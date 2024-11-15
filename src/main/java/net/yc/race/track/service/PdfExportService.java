package net.yc.race.track.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.springframework.stereotype.Service;
import net.yc.race.track.model.Result;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class PdfExportService {

    public void exportResultsToPdf(List<Result> results, String outputPath) throws DocumentException, IOException {
        // Création du document PDF
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(outputPath));

        // Ouvrir le document
        document.open();

        // Ajouter un titre
        Paragraph title = new Paragraph("Résultats de la compétition", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18));
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        // Ajouter une ligne de séparation
        document.add(new Paragraph("\n"));

        // Créer une table pour afficher les résultats
        PdfPTable table = new PdfPTable(5);  // 5 colonnes : Rang, Nom, Temps, Distance, Vitesse

        // Ajouter les en-têtes
        table.addCell("Rang");
        table.addCell("Nom");
        table.addCell("Temps");
        table.addCell("Distance (km)");
        table.addCell("Vitesse (m/min)");

        // Ajouter les lignes de résultats
        for (Result result : results) {
            table.addCell(String.valueOf(result.getRank()));
            table.addCell(result.getLoftName());
            table.addCell(String.valueOf(result.getArriveHour()));
            table.addCell(String.valueOf(result.getDistance()));
            table.addCell(String.valueOf(result.getSpeed()));
        }

        // Ajouter la table au document
        document.add(table);

        // Fermer le document
        document.close();
    }
}