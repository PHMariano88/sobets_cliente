/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senai.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Paulo
 */
public class ArquivoAnexo {

    private final String PASTA_DESTINO = "anexo_requerimento";

    public String upload(String nomeArquivo, InputStream arquivoCarregado) {
        String caminho = criaCaminho() + File.separator + nomeArquivo;
        File novoArquivoDestino = null;
        try {
            novoArquivoDestino = new File(caminho);
            FileOutputStream destino = new FileOutputStream(novoArquivoDestino);
            copiar(arquivoCarregado, destino);
        } catch (Exception ex) {
            Logger.getLogger(ArquivoAnexo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return novoArquivoDestino.getAbsolutePath();
    }

    private void copiar(InputStream origem, OutputStream destino) {
        int byteTransferencia = 0;
        byte tamanhoMaximo[] = new byte[1024 * 10];
        try {
            while ((byteTransferencia = origem.read()) >= 0) {
                destino.write(tamanhoMaximo, 0, byteTransferencia);
            }
            destino.close();
        } catch (IOException ex) {
            Logger.getLogger(ArquivoAnexo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String criaCaminho() {
        File file = new File(PASTA_DESTINO);
        if (!file.exists()) {
            file.mkdir();
        }
        return file.getAbsolutePath();
    }

}
