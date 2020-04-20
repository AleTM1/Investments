package com.company;

import main_structure.Portafoglio;
import main_structure.Titolo;

import java.io.FileWriter;
import java.io.IOException;

class Printer {

     void printStructure(Portafoglio root) {
        try {
            FileWriter writer = new FileWriter("src/printer_results/structure.txt", false);

            dfsPrint(writer, root);

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void dfsPrint(FileWriter writer, Portafoglio portafoglio) throws IOException{
        writer.write("portafoglio " + portafoglio.getId());
        writer.write("\r\n");
        for(Titolo t : portafoglio.getArrayTitoli()){
            if(t instanceof Portafoglio)
                dfsPrint(writer, (Portafoglio)t);
        }
    }

}
