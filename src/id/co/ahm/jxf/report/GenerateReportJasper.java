package id.co.ahm.jxf.report;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRTextExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimpleTextExporterConfiguration;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;
import net.sf.jasperreports.export.SimpleXlsExporterConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component(value = "generateReportJasper")
public class GenerateReportJasper {

    @Autowired
    private DataSource dataSource;

    @Value("${path-report}")
    private String pathDefaultReport;

    @Value("${path-report-generate}")
    private String pathReportGenerate;

    public String getPathDefaultReport() {
        return pathDefaultReport;
    }

    @Deprecated
    public File createReport(String pathReport, String reportName, String formatReport, Map m) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        String dateTime = sdf.format(new Date());
        Connection con = null;
        JasperPrint jasperPrint;
        try {
            con = dataSource.getConnection();
            jasperPrint = JasperFillManager.fillReport(pathReport, m, con);
            SimpleExporterInput exporterInput = new SimpleExporterInput(jasperPrint);
            if ("pdf".equalsIgnoreCase(formatReport)) {
                JRPdfExporter exporter = new JRPdfExporter();
                SimplePdfExporterConfiguration config = new SimplePdfExporterConfiguration();
                exporter.setConfiguration(config);
                SimpleOutputStreamExporterOutput writer = new SimpleOutputStreamExporterOutput(reportName + "." + formatReport);
                exporter.setExporterOutput(writer);
                exporter.setExporterInput(exporterInput);
                exporter.exportReport();
            } else if ("xls".equalsIgnoreCase(formatReport)) {
                JRXlsExporter exporter = new JRXlsExporter();
                SimpleXlsExporterConfiguration config = new SimpleXlsExporterConfiguration();
                exporter.setConfiguration(config);
                SimpleOutputStreamExporterOutput writer = new SimpleOutputStreamExporterOutput(reportName + "." + formatReport);
                exporter.setExporterOutput(writer);
                exporter.setExporterInput(exporterInput);
                exporter.exportReport();
            } else if ("txt".equalsIgnoreCase(formatReport)) {
                JRTextExporter exporter = new JRTextExporter();
                SimpleTextExporterConfiguration config = new SimpleTextExporterConfiguration();
                exporter.setConfiguration(config);
                SimpleWriterExporterOutput writer = new SimpleWriterExporterOutput(reportName + "." + formatReport);
                exporter.setExporterOutput(writer);
                exporter.setExporterInput(exporterInput);
                exporter.exportReport();
            }
        } catch (JRException e) {
            e.printStackTrace();
        } catch (SQLException ex) {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex1) {
                    ex1.printStackTrace();
                }
            }
            ex.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex1) {
                    ex1.printStackTrace();
                }
            }
        }
        return new File(reportName + "." + formatReport);
    }

    public File generateJasperFile(String jasperName, String reportName, String formatReport, Map m, String ext) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        String dateTime = sdf.format(new Date());
        Connection con = null;
        JasperPrint jasperPrint;
        try {
            con = dataSource.getConnection();
            jasperPrint = JasperFillManager.fillReport(pathDefaultReport + jasperName, m, con);
            SimpleExporterInput exporterInput = new SimpleExporterInput(jasperPrint);
            if ("pdf".equalsIgnoreCase(formatReport)) {
                if (ext != null) {
                    formatReport = ext;
                }
                JRPdfExporter exporter = new JRPdfExporter();
                SimplePdfExporterConfiguration config = new SimplePdfExporterConfiguration();
                exporter.setConfiguration(config);
                SimpleOutputStreamExporterOutput writer = new SimpleOutputStreamExporterOutput(pathReportGenerate + reportName + dateTime + "." + formatReport);
                exporter.setExporterOutput(writer);
                exporter.setExporterInput(exporterInput);
                exporter.exportReport();
            } else if ("xls".equalsIgnoreCase(formatReport)) {
                if (ext != null) {
                    formatReport = ext;
                }
                JRXlsExporter exporter = new JRXlsExporter();
                SimpleXlsExporterConfiguration config = new SimpleXlsExporterConfiguration();
                exporter.setConfiguration(config);
                SimpleOutputStreamExporterOutput writer = new SimpleOutputStreamExporterOutput(pathReportGenerate + reportName + dateTime + "." + formatReport);
                exporter.setExporterOutput(writer);
                exporter.setExporterInput(exporterInput);
                exporter.exportReport();
            } else if ("txt".equalsIgnoreCase(formatReport)) {
                if (ext != null) {
                    formatReport = ext;
                }
                JRTextExporter exporter = new JRTextExporter();
                SimpleTextExporterConfiguration config = new SimpleTextExporterConfiguration();
                exporter.setConfiguration(config);
                SimpleWriterExporterOutput writer = new SimpleWriterExporterOutput(pathReportGenerate + reportName + dateTime + "." + formatReport);
                exporter.setExporterOutput(writer);
                exporter.setExporterInput(exporterInput);
                exporter.exportReport();
            }
        } catch (JRException e) {
            e.printStackTrace();
        } catch (SQLException ex) {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex1) {
                    ex1.printStackTrace();
                }
            }
            ex.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex1) {
                    ex1.printStackTrace();
                }
            }
        }
        return new File(pathReportGenerate + reportName + dateTime + "." + formatReport);
    }

    public File generateJasperFile(String jasperName, String reportName, String formatReport, Map m) {
        return generateJasperFile(jasperName, reportName, formatReport, m, null);
    }

    public File generateJasperFileWithConn(Connection con, String jasperName, String reportName, String formatReport, Map m) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        String dateTime = sdf.format(new Date());
        JasperPrint jasperPrint;
        try {
            jasperPrint = JasperFillManager.fillReport(pathDefaultReport + jasperName, m, con);
            SimpleExporterInput exporterInput = new SimpleExporterInput(jasperPrint);
            if ("pdf".equalsIgnoreCase(formatReport)) {
                JRPdfExporter exporter = new JRPdfExporter();
                SimplePdfExporterConfiguration config = new SimplePdfExporterConfiguration();
                exporter.setConfiguration(config);
                SimpleOutputStreamExporterOutput writer = new SimpleOutputStreamExporterOutput(pathReportGenerate + reportName + dateTime + "." + formatReport);
                exporter.setExporterOutput(writer);
                exporter.setExporterInput(exporterInput);
                exporter.exportReport();
            } else if ("xls".equalsIgnoreCase(formatReport)) {
                JRXlsExporter exporter = new JRXlsExporter();
                SimpleXlsExporterConfiguration config = new SimpleXlsExporterConfiguration();
                exporter.setConfiguration(config);
                SimpleOutputStreamExporterOutput writer = new SimpleOutputStreamExporterOutput(pathReportGenerate + reportName + dateTime + "." + formatReport);
                exporter.setExporterOutput(writer);
                exporter.setExporterInput(exporterInput);
                exporter.exportReport();
            } else if ("txt".equalsIgnoreCase(formatReport)) {
                JRTextExporter exporter = new JRTextExporter();
                SimpleTextExporterConfiguration config = new SimpleTextExporterConfiguration();
                exporter.setConfiguration(config);
                SimpleWriterExporterOutput writer = new SimpleWriterExporterOutput(pathReportGenerate + reportName + dateTime + "." + formatReport);
                exporter.setExporterOutput(writer);
                exporter.setExporterInput(exporterInput);
                exporter.exportReport();
            }
        } catch (JRException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex1) {
                    ex1.printStackTrace();
                }
            }
        }
        return new File(pathReportGenerate + reportName + dateTime + "." + formatReport);
    }

    public byte[] generateJasperPdf(String jasperName, Map m) throws JRException {
        Connection con = null;
        JasperPrint jasperPrint = null;
        try {
            con = dataSource.getConnection();
            jasperPrint = JasperFillManager.fillReport(pathDefaultReport + jasperName, m, con);
        } catch (JRException e) {
            e.printStackTrace();
        } catch (SQLException ex) {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex1) {
                    ex1.printStackTrace();
                }
            }
            ex.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex1) {
                    ex1.printStackTrace();
                }
            }
        }
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

    public byte[] generateJasperPdfWhiteConn(Connection con, String jasperName, Map m) throws JRException {
        JasperPrint jasperPrint = null;
        try {
            jasperPrint = JasperFillManager.fillReport(pathDefaultReport + jasperName, m, con);
        } catch (JRException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex1) {
                    ex1.printStackTrace();
                }
            }
        }
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

}
