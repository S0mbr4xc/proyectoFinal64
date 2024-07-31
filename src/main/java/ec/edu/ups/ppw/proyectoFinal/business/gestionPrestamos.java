package ec.edu.ups.ppw.proyectoFinal.business;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ec.edu.ups.ppw.proyectoFinal.DAO.prestamoDAO;
import ec.edu.ups.ppw.proyectoFinal.model.libro;
import ec.edu.ups.ppw.proyectoFinal.model.prestamo;
import ec.edu.ups.ppw.proyectoFinal.model.usuario;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import com.itextpdf.io.IOException;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;

@Stateless
public class gestionPrestamos {

    @Inject
    private prestamoDAO dao;
    
    public void crear(prestamo us) {
        dao.insert(us);
    }
    
    public prestamo read(String correo) {
        return dao.read(correo);
    }
    
    public void update(prestamo us) {
        dao.update(us);
    }
    
    public void delete(String correo) {
        dao.delete(correo);
    }
    
    public List<prestamo> getAll(){
        return dao.getAll();
    }
    
    public List<prestamo> getUsuario(String usuario){
        return dao.getUsuario(usuario);
    }
    
    public List<prestamo> getLibro(String libro){
        return dao.getPrestamosByNombreLibro(libro);
    }
    
    public byte[] generarReporteHistorialPrestamosPDF() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            PdfWriter writer = new PdfWriter(baos);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            List<usuario> usuarios = dao.getUsuariosConPrestamos();

            for (usuario user : usuarios) {
                List<prestamo> prestamos = dao.getPrestamosPorUsuario(user);

                long totalReservas = prestamos.stream().count();
                long totalPrestados = prestamos.stream().filter(p -> p.getEstado().equals("Prestado")).count();
                long totalAtrasados = prestamos.stream().filter(p -> p.getEstado().equals("Atrasado")).count();
                long totalDevueltos = prestamos.stream().filter(p -> p.getEstado().equals("Devuelto")).count();

                document.add(new Paragraph("Usuario: " + user.getUsuario()));
                document.add(new Paragraph("Total Reservas: " + totalReservas));
                document.add(new Paragraph("Libros Prestados: " + totalPrestados));
                document.add(new Paragraph("Libros Atrasados: " + totalAtrasados));
                document.add(new Paragraph("Libros Devueltos: " + totalDevueltos));

                Table table = new Table(4);
                table.addCell(new Cell().add(new Paragraph("Libro")));
                table.addCell(new Cell().add(new Paragraph("Fecha Inicio")));
                table.addCell(new Cell().add(new Paragraph("Fecha Fin")));
                table.addCell(new Cell().add(new Paragraph("Estado")));

                for (prestamo prestamo : prestamos) {
                    table.addCell(new Cell().add(new Paragraph(prestamo.getLibro().getNombre())));
                    table.addCell(new Cell().add(new Paragraph(prestamo.getFechaInicio().toString())));
                    table.addCell(new Cell().add(new Paragraph(prestamo.getFechaFin().toString())));
                    table.addCell(new Cell().add(new Paragraph(prestamo.getEstado())));
                }

                document.add(table);
                document.add(new Paragraph("\n"));
            }

            List<libro> librosReservados = dao.getAll().stream()
                .filter(p -> p.getEstado().equals("Reservado"))
                .map(prestamo::getLibro)
                .collect(Collectors.groupingBy(libro::getNombre, Collectors.counting()))
                .entrySet().stream()
                .sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
                .limit(5)
                .map(Map.Entry::getKey)
                .map(dao::getLibroByName)
                .collect(Collectors.toList());

            document.add(new Paragraph("Top 5 Libros Más Reservados:"));

            Table topLibrosTable = new Table(2);
            topLibrosTable.addHeaderCell(new Cell().add(new Paragraph("Libro")));
            topLibrosTable.addHeaderCell(new Cell().add(new Paragraph("Cantidad de Reservas")));

            for (libro libro : librosReservados) {
                long count = dao.getCountReservasByLibro(libro.getNombre());
                topLibrosTable.addCell(new Cell().add(new Paragraph(libro.getNombre())));
                topLibrosTable.addCell(new Cell().add(new Paragraph(String.valueOf(count))));
            }

            document.add(topLibrosTable);

            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return baos.toByteArray();
    }

    public byte[] generarReporteReservasEntreFechasPDF(LocalDate fechaInicio, LocalDate fechaFin) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            PdfWriter writer = new PdfWriter(baos);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            List<prestamo> reservas = dao.getReservasEntreFechas(fechaInicio, fechaFin);

            long totalReservas = reservas.size();
            long totalPrestados = reservas.stream().filter(p -> p.getEstado().equals("Prestado")).count();
            long totalAtrasados = reservas.stream().filter(p -> p.getEstado().equals("Atrasado")).count();
            long totalDevueltos = reservas.stream().filter(p -> p.getEstado().equals("Devuelto")).count();

            document.add(new Paragraph("Reporte de Reservas entre " + fechaInicio + " y " + fechaFin));
            document.add(new Paragraph("Total Reservas: " + totalReservas));
            document.add(new Paragraph("Total Prestados: " + totalPrestados));
            document.add(new Paragraph("Total Atrasados: " + totalAtrasados));
            document.add(new Paragraph("Total Devueltos: " + totalDevueltos));
            document.add(new Paragraph("\n"));

            Table table = new Table(5);
            table.addHeaderCell(new Cell().add(new Paragraph("Usuario")));
            table.addHeaderCell(new Cell().add(new Paragraph("Libro")));
            table.addHeaderCell(new Cell().add(new Paragraph("Fecha Inicio")));
            table.addHeaderCell(new Cell().add(new Paragraph("Fecha Fin")));
            table.addHeaderCell(new Cell().add(new Paragraph("Estado")));

            for (prestamo prestamo : reservas) {
                table.addCell(new Cell().add(new Paragraph(prestamo.getUsuario().getUsuario())));
                table.addCell(new Cell().add(new Paragraph(prestamo.getLibro().getNombre())));
                table.addCell(new Cell().add(new Paragraph(prestamo.getFechaInicio().toString())));
                table.addCell(new Cell().add(new Paragraph(prestamo.getFechaFin().toString())));
                table.addCell(new Cell().add(new Paragraph(prestamo.getEstado())));
            }

            document.add(table);
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return baos.toByteArray();
    }
}
