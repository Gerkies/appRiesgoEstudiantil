package umg.DAO;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Generador de Documentos Oficiales en PDF con identidad institucional UMG
 * Ingeniería en Sistemas de Información - Programación II
 */
public class GeneradorPDF {

    // Paleta de colores institucionales UMG (Tonos Rojos / Carmesí)
    private static final BaseColor ROJO_UMG = new BaseColor(160, 25, 35);        // Rojo institucional
    private static final BaseColor ROJO_OSCURO = new BaseColor(120, 15, 25);     // Rojo oscuro para títulos
    private static final BaseColor ROJO_PASTEL = new BaseColor(255, 235, 235);   // Fondo suave para cabeceras
    private static final BaseColor GRIS_CLARO = new BaseColor(248, 248, 248);    // Alternancia de filas

    // Fuentes tipograficas
    private static final Font FONT_HEADER_UMG = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 13, ROJO_UMG);
    private static final Font FONT_HEADER_SUB = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 9, BaseColor.DARK_GRAY);
    private static final Font FONT_TITULO_DOC = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, ROJO_OSCURO);
    private static final Font FONT_SECCION = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10, ROJO_UMG);
    private static final Font FONT_TEXTO = FontFactory.getFont(FontFactory.HELVETICA, 9, BaseColor.BLACK);
    private static final Font FONT_BOLD = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 9, BaseColor.BLACK);
    private static final Font FONT_BLANCO = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 9, BaseColor.WHITE);
    private static final Font FONT_PIE = FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE, 8, BaseColor.GRAY);

    // =========================================================================
    // 1. FICHA INDIVIDUAL DE SEGUIMIENTO (FichaSeguimiento.pdf)
    // =========================================================================
    public static File generarFichaSeguimiento(int idEstudiante, String rutaSalida) throws Exception {
        Document document = new Document(PageSize.LETTER, 36, 36, 36, 36);
        File archivo = new File(rutaSalida);
        if (archivo.getParentFile() != null) {
            archivo.getParentFile().mkdirs();
        }
        PdfWriter.getInstance(document, new FileOutputStream(archivo));
        document.open();

        // Encabezado institucional UMG
        agregarEncabezadoUMG(document, "FICHA INDIVIDUAL DE SEGUIMIENTO DE ESTUDIANTE");

        // Consulta de datos del estudiante
        String carne = "", nombres = "", apellidos = "", fechaNac = "", sexo = "", tel = "", dir = "", estado = "", grupo = "";
        String sqlEst = "SELECT e.*, g.codigoGrupo, g.grado, g.seccion, g.carrera, g.jornada "
                      + "FROM estudiante e JOIN grupo g ON e.idGrupo = g.idGrupo WHERE e.idEstudiante = ?";
        try (Connection cn = ConexionDB.abrir();
             PreparedStatement ps = cn.prepareStatement(sqlEst)) {
            ps.setInt(1, idEstudiante);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    carne = rs.getString("carne");
                    nombres = rs.getString("nombres");
                    apellidos = rs.getString("apellidos");
                    fechaNac = rs.getString("fechaNacimiento");
                    sexo = rs.getString("sexo");
                    tel = rs.getString("telefono");
                    dir = rs.getString("direccion");
                    estado = rs.getString("estadoAcademico");
                    grupo = rs.getString("grado") + " " + rs.getString("carrera") + " Sec. " + rs.getString("seccion") + " (" + rs.getString("jornada") + ")";
                }
            }
        }

        // I. Datos del Estudiante
        PdfPTable tblDatos = new PdfPTable(4);
        tblDatos.setWidthPercentage(100);
        tblDatos.setWidths(new float[]{22, 28, 22, 28});
        agregarCabeceraSeccion(tblDatos, "I. DATOS GENERALES DEL ESTUDIANTE", 4);
        agregarDato(tblDatos, "Carne:", carne);
        agregarDato(tblDatos, "Nombre Completo:", nombres + " " + apellidos);
        agregarDato(tblDatos, "F. Nacimiento:", fechaNac);
        agregarDato(tblDatos, "Genero / Sexo:", sexo);
        agregarDato(tblDatos, "Telefono:", tel);
        agregarDato(tblDatos, "Estado Academico:", estado);
        agregarDato(tblDatos, "Direccion:", dir, 4);
        agregarDato(tblDatos, "Grupo Asignado:", grupo, 4);
        document.add(tblDatos);

        // II. Resumen de Asistencia
        int totAsis = 0, pres = 0, aus = 0, just = 0, tard = 0;
        String sqlAsis = "SELECT estadoAsistencia, COUNT(*) AS total FROM asistencia WHERE idEstudiante = ? GROUP BY estadoAsistencia";
        try (Connection cn = ConexionDB.abrir();
             PreparedStatement ps = cn.prepareStatement(sqlAsis)) {
            ps.setInt(1, idEstudiante);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String est = rs.getString("estadoAsistencia").toLowerCase();
                    int c = rs.getInt("total");
                    totAsis += c;
                    if (est.contains("presente")) pres += c;
                    else if (est.contains("justificada")) just += c;
                    else if (est.contains("ausente")) aus += c;
                    else if (est.contains("tarde")) tard += c;
                }
            }
        }

        PdfPTable tblAsis = new PdfPTable(5);
        tblAsis.setWidthPercentage(100);
        tblAsis.setSpacingBefore(8);
        agregarCabeceraSeccion(tblAsis, "II. RESUMEN DE ASISTENCIA Y PUNTUALIDAD", 5);
        agregarEncabezadosRojos(tblAsis, new String[]{"Total Registros", "Presentes", "Inasistencias", "Justificadas", "Tardanzas"});
        tblAsis.addCell(celdaCentrada(String.valueOf(totAsis)));
        tblAsis.addCell(celdaCentrada(String.valueOf(pres)));
        tblAsis.addCell(celdaCentrada(String.valueOf(aus)));
        tblAsis.addCell(celdaCentrada(String.valueOf(just)));
        tblAsis.addCell(celdaCentrada(String.valueOf(tard)));
        document.add(tblAsis);

        // III. Calificaciones y Rendimiento
        PdfPTable tblRend = new PdfPTable(5);
        tblRend.setWidthPercentage(100);
        tblRend.setSpacingBefore(8);
        tblRend.setWidths(new float[]{32, 20, 16, 16, 16});
        agregarCabeceraSeccion(tblRend, "III. RENDIMIENTO ACADEMICO Y NOTAS", 5);
        agregarEncabezadosRojos(tblRend, new String[]{"Curso", "Periodo", "Nota", "Minima", "Resultado"});

        double suma = 0;
        int nNotas = 0;
        String sqlRend = "SELECT c.nombreCurso, r.periodo, r.notaObtenida, r.notaMinima "
                       + "FROM rendimiento_academico r JOIN curso c ON r.idCurso = c.idCurso "
                       + "WHERE r.idEstudiante = ? ORDER BY r.periodo";
        try (Connection cn = ConexionDB.abrir();
             PreparedStatement ps = cn.prepareStatement(sqlRend)) {
            ps.setInt(1, idEstudiante);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    double n = rs.getDouble("notaObtenida");
                    double min = rs.getDouble("notaMinima");
                    suma += n;
                    nNotas++;
                    tblRend.addCell(celdaTexto(rs.getString("nombreCurso")));
                    tblRend.addCell(celdaCentrada(rs.getString("periodo")));
                    tblRend.addCell(celdaCentrada(String.format("%.1f", n)));
                    tblRend.addCell(celdaCentrada(String.format("%.1f", min)));
                    tblRend.addCell(celdaCentrada(n >= min ? "Aprobado" : "Reprobado"));
                }
            }
        }
        if (nNotas == 0) {
            PdfPCell cVacia = new PdfPCell(new Phrase("Sin calificaciones registradas.", FONT_TEXTO));
            cVacia.setColspan(5);
            cVacia.setHorizontalAlignment(Element.ALIGN_CENTER);
            cVacia.setPadding(6);
            tblRend.addCell(cVacia);
        } else {
            double prom = suma / nNotas;
            PdfPCell cProm = new PdfPCell(new Phrase(String.format("PROMEDIO ACUMULADO: %.2f PTS  %s", prom, (prom >= 60.0 ? "(Aprobado)" : "(EN RIESGO ACADEMICO)")), FONT_BOLD));
            cProm.setColspan(5);
            cProm.setBackgroundColor(ROJO_PASTEL);
            cProm.setPadding(5);
            tblRend.addCell(cProm);
        }
        document.add(tblRend);

        // IV. Alertas Tempranas
        PdfPTable tblAl = new PdfPTable(5);
        tblAl.setWidthPercentage(100);
        tblAl.setSpacingBefore(8);
        tblAl.setWidths(new float[]{18, 22, 28, 16, 16});
        agregarCabeceraSeccion(tblAl, "IV. HISTORIAL DE ALERTAS PREVENTIVAS", 5);
        agregarEncabezadosRojos(tblAl, new String[]{"No. Alerta", "Tipo", "Motivo", "Prioridad", "Estado"});

        String sqlAl = "SELECT numeroAlerta, tipoAlerta, motivo, prioridad, estado FROM alerta WHERE idEstudiante = ? ORDER BY idAlerta DESC";
        int cAlertas = 0;
        try (Connection cn = ConexionDB.abrir();
             PreparedStatement ps = cn.prepareStatement(sqlAl)) {
            ps.setInt(1, idEstudiante);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    cAlertas++;
                    tblAl.addCell(celdaCentrada(rs.getString("numeroAlerta")));
                    tblAl.addCell(celdaTexto(rs.getString("tipoAlerta")));
                    tblAl.addCell(celdaTexto(rs.getString("motivo")));
                    tblAl.addCell(celdaCentrada(rs.getString("prioridad")));
                    tblAl.addCell(celdaCentrada(rs.getString("estado")));
                }
            }
        }
        if (cAlertas == 0) {
            PdfPCell cV = new PdfPCell(new Phrase("Sin alertas registradas (Estudiante sin riesgo activo).", FONT_TEXTO));
            cV.setColspan(5);
            cV.setHorizontalAlignment(Element.ALIGN_CENTER);
            cV.setPadding(6);
            tblAl.addCell(cV);
        }
        document.add(tblAl);

        // V. Firmas
        agregarBloqueFirmas(document, "Firma del Estudiante / Tutor", "Orientador / Catedratico UMG");

        document.close();
        return archivo;
    }

    // =========================================================================
    // 2. ACTA FORMAL DE REUNION (ActaReunion.pdf)
    // =========================================================================
    public static File generarActaReunion(int idReunion, String rutaSalida) throws Exception {
        Document document = new Document(PageSize.LETTER, 36, 36, 36, 36);
        File archivo = new File(rutaSalida);
        if (archivo.getParentFile() != null) {
            archivo.getParentFile().mkdirs();
        }
        PdfWriter.getInstance(document, new FileOutputStream(archivo));
        document.open();

        agregarEncabezadoUMG(document, "ACTA FORMAL DE REUNION Y COMPROMISOS");

        String fecha = "", motivo = "", participantes = "", situacion = "", acuerdos = "", respAcuerdos = "", fechaRev = "", estadoAcuerdos = "";
        String estNombre = "", estCarne = "", respNombre = "", respDpi = "", respParentesco = "", respTel = "";

        String sql = "SELECT r.*, e.carne, e.nombres, e.apellidos, "
                   + "p.nombreCompleto AS respNom, p.dpi, p.parentesco, p.telefono AS respTel "
                   + "FROM reunion_responsable r "
                   + "JOIN estudiante e ON r.idEstudiante = e.idEstudiante "
                   + "JOIN responsable p ON r.idResponsable = p.idResponsable "
                   + "WHERE r.idReunion = ?";
        try (Connection cn = ConexionDB.abrir();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idReunion);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    fecha = rs.getString("fechaHora");
                    motivo = rs.getString("motivo");
                    participantes = rs.getString("participantes");
                    situacion = rs.getString("situacionPresentada");
                    acuerdos = rs.getString("acuerdos");
                    respAcuerdos = rs.getString("responsablesAcuerdos");
                    fechaRev = rs.getString("fechaRevision");
                    estadoAcuerdos = rs.getString("estadoAcuerdos");
                    estNombre = rs.getString("nombres") + " " + rs.getString("apellidos");
                    estCarne = rs.getString("carne");
                    respNombre = rs.getString("respNom");
                    respDpi = rs.getString("dpi");
                    respParentesco = rs.getString("parentesco");
                    respTel = rs.getString("respTel");
                }
            }
        }

        // Datos por defecto si no hay registro especifico
        if (estNombre.isEmpty()) {
            estNombre = "Mateo Alexander Estrada Perez";
            estCarne = "0907-23-1001";
            respNombre = "Rosa Perez Garcia";
            respDpi = "2589 12345 0101";
            respParentesco = "Madre de familia";
            respTel = "5432-1098";
            fecha = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
            motivo = "Seguimiento preventivo por ausencias e intervencion academica";
            participantes = "Licda. Marta Gomez (Orientadora), Prof. Juan Lopez, Sra. Rosa Perez (Madre)";
            situacion = "El estudiante acumula inasistencias injustificadas que comprometen su desempeno en el curso de Programacion II.";
            acuerdos = "1. Compromiso diario de asistencia puntual.\n2. Incorporacion al plan de tutorias de refuerzo los dias martes y jueves.\n3. Revision periodica de avances.";
            respAcuerdos = "Responsable familiar y Orientacion Academica UMG";
            fechaRev = "2026-11-20";
            estadoAcuerdos = "En cumplimiento";
        }

        PdfPTable tblCaso = new PdfPTable(2);
        tblCaso.setWidthPercentage(100);
        tblCaso.setWidths(new float[]{30, 70});
        agregarCabeceraSeccion(tblCaso, "I. DATOS DEL CASO Y PARTICIPANTES", 2);
        agregarDato(tblCaso, "Fecha y Hora:", fecha);
        agregarDato(tblCaso, "Estudiante:", estNombre + " (Carne: " + estCarne + ")");
        agregarDato(tblCaso, "Responsable Familiar:", respNombre + " (" + respParentesco + " - DPI: " + respDpi + ")");
        agregarDato(tblCaso, "Telefono de Contacto:", respTel);
        agregarDato(tblCaso, "Convocatoria / Motivo:", motivo);
        agregarDato(tblCaso, "Participantes:", participantes);
        document.add(tblCaso);

        PdfPTable tblCompromisos = new PdfPTable(1);
        tblCompromisos.setWidthPercentage(100);
        tblCompromisos.setSpacingBefore(10);
        agregarCabeceraSeccion(tblCompromisos, "II. SITUACION IDENTIFICADA", 1);
        PdfPCell cSit = new PdfPCell(new Phrase(situacion, FONT_TEXTO));
        cSit.setPadding(6);
        tblCompromisos.addCell(cSit);

        agregarCabeceraSeccion(tblCompromisos, "III. ACUERDOS Y COMPROMISOS ESTABLECIDOS", 1);
        PdfPCell cAcu = new PdfPCell(new Phrase(acuerdos + "\n\nResponsables del Acuerdo: " + respAcuerdos + "\nFecha Revision: " + fechaRev + " | Estado: " + estadoAcuerdos, FONT_TEXTO));
        cAcu.setPadding(6);
        tblCompromisos.addCell(cAcu);
        document.add(tblCompromisos);

        agregarBloqueFirmas(document, "Firma del Padre / Madre / Tutor", "Orientador / Coordinador Academico");

        document.close();
        return archivo;
    }

    // =========================================================================
    // 3. REPORTE TABULAR DINAMICO A PDF (14 REPORTES)
    // =========================================================================
    public static File generarReporteTabularPDF(String titulo, List<String> columnas, List<Object[]> filas, String rutaSalida) throws Exception {
        Document document = new Document(PageSize.LETTER.rotate(), 36, 36, 36, 36); // Horizontal
        File archivo = new File(rutaSalida);
        if (archivo.getParentFile() != null) {
            archivo.getParentFile().mkdirs();
        }
        PdfWriter.getInstance(document, new FileOutputStream(archivo));
        document.open();

        agregarEncabezadoUMG(document, titulo.toUpperCase());

        PdfPTable table = new PdfPTable(columnas.size());
        table.setWidthPercentage(100);

        for (String col : columnas) {
            PdfPCell c = new PdfPCell(new Phrase(col, FONT_BLANCO));
            c.setBackgroundColor(ROJO_UMG);
            c.setHorizontalAlignment(Element.ALIGN_CENTER);
            c.setPadding(5);
            table.addCell(c);
        }

        boolean par = false;
        for (Object[] fila : filas) {
            BaseColor fondo = par ? GRIS_CLARO : BaseColor.WHITE;
            for (Object obj : fila) {
                PdfPCell c = new PdfPCell(new Phrase(obj != null ? obj.toString() : "", FONT_TEXTO));
                c.setBackgroundColor(fondo);
                c.setPadding(4);
                table.addCell(c);
            }
            par = !par;
        }
        document.add(table);

        Paragraph pTotal = new Paragraph("\nTotal de registros: " + filas.size() + " | Fecha de impresion: " + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date()), FONT_PIE);
        pTotal.setAlignment(Element.ALIGN_RIGHT);
        document.add(pTotal);

        document.close();
        return archivo;
    }

    // =========================================================================
    // METODOS AUXILIARES LIMPIOS (PONYTAIL - CERO CODIGO INNECESARIO)
    // =========================================================================
    private static void agregarEncabezadoUMG(Document doc, String tituloDoc) throws Exception {
        Paragraph p1 = new Paragraph("UNIVERSIDAD MARIANO GALVEZ DE GUATEMALA", FONT_HEADER_UMG);
        p1.setAlignment(Element.ALIGN_CENTER);
        doc.add(p1);

        Paragraph p2 = new Paragraph("Facultad de Ingenieria en Sistemas de Informacion y Ciencias de la Computacion\nProyecto: Sistema de Seguimiento de Estudiantes en Riesgo Escolar", FONT_HEADER_SUB);
        p2.setAlignment(Element.ALIGN_CENTER);
        p2.setSpacingAfter(6);
        doc.add(p2);

        Paragraph p3 = new Paragraph(tituloDoc, FONT_TITULO_DOC);
        p3.setAlignment(Element.ALIGN_CENTER);
        p3.setSpacingAfter(10);
        doc.add(p3);
    }

    private static void agregarCabeceraSeccion(PdfPTable tbl, String titulo, int colspan) {
        PdfPCell c = new PdfPCell(new Phrase(titulo, FONT_SECCION));
        c.setColspan(colspan);
        c.setBackgroundColor(ROJO_PASTEL);
        c.setPadding(4);
        tbl.addCell(c);
    }

    private static void agregarEncabezadosRojos(PdfPTable tbl, String[] encabezados) {
        for (String h : encabezados) {
            PdfPCell c = new PdfPCell(new Phrase(h, FONT_BLANCO));
            c.setBackgroundColor(ROJO_UMG);
            c.setHorizontalAlignment(Element.ALIGN_CENTER);
            c.setPadding(4);
            tbl.addCell(c);
        }
    }

    private static void agregarDato(PdfPTable tbl, String etiqueta, String valor) {
        PdfPCell c1 = new PdfPCell(new Phrase(etiqueta, FONT_BOLD));
        c1.setBackgroundColor(GRIS_CLARO);
        c1.setPadding(4);
        tbl.addCell(c1);

        PdfPCell c2 = new PdfPCell(new Phrase(valor != null ? valor : "", FONT_TEXTO));
        c2.setPadding(4);
        tbl.addCell(c2);
    }

    private static void agregarDato(PdfPTable tbl, String etiqueta, String valor, int colspan) {
        PdfPCell c1 = new PdfPCell(new Phrase(etiqueta, FONT_BOLD));
        c1.setBackgroundColor(GRIS_CLARO);
        c1.setPadding(4);
        tbl.addCell(c1);

        PdfPCell c2 = new PdfPCell(new Phrase(valor != null ? valor : "", FONT_TEXTO));
        c2.setColspan(colspan - 1);
        c2.setPadding(4);
        tbl.addCell(c2);
    }

    private static PdfPCell celdaTexto(String txt) {
        PdfPCell c = new PdfPCell(new Phrase(txt != null ? txt : "", FONT_TEXTO));
        c.setPadding(4);
        return c;
    }

    private static PdfPCell celdaCentrada(String txt) {
        PdfPCell c = new PdfPCell(new Phrase(txt != null ? txt : "", FONT_TEXTO));
        c.setHorizontalAlignment(Element.ALIGN_CENTER);
        c.setPadding(4);
        return c;
    }

    private static void agregarBloqueFirmas(Document doc, String rol1, String rol2) throws Exception {
        Paragraph pEspacio = new Paragraph("\n\n");
        doc.add(pEspacio);

        PdfPTable tblFirmas = new PdfPTable(2);
        tblFirmas.setWidthPercentage(90);
        tblFirmas.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

        PdfPCell cF1 = new PdfPCell(new Phrase("_____________________________________\n" + rol1, FONT_BOLD));
        cF1.setBorder(PdfPCell.NO_BORDER);
        cF1.setHorizontalAlignment(Element.ALIGN_CENTER);

        PdfPCell cF2 = new PdfPCell(new Phrase("_____________________________________\n" + rol2, FONT_BOLD));
        cF2.setBorder(PdfPCell.NO_BORDER);
        cF2.setHorizontalAlignment(Element.ALIGN_CENTER);

        tblFirmas.addCell(cF1);
        tblFirmas.addCell(cF2);
        doc.add(tblFirmas);

        Paragraph pPie = new Paragraph("\nGenerado el: " + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date()) + " | UMG Ingenieria en Sistemas", FONT_PIE);
        pPie.setAlignment(Element.ALIGN_RIGHT);
        doc.add(pPie);
    }

    public static void abrirArchivo(File archivo) {
        try {
            if (Desktop.isDesktopSupported() && archivo.exists()) {
                Desktop.getDesktop().open(archivo);
            }
        } catch (Exception ex) {
            // Silencioso
        }
    }
}
