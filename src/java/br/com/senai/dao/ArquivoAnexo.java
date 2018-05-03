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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Paulo
 */
public class ArquivoAnexo {

    private final String PASTA_DESTINO = "anexo_requerimentos";
    
    public void upload(String nomeArquivo, InputStream 
            arquivoCarregado) {
        String caminho = PASTA_DESTINO + File.separator + nomeArquivo;
        File novoArquivo = new File(caminho);
        try {
            FileOutputStream saida = new FileOutputStream(novoArquivo);
            copiar(arquivoCarregado, saida);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ArquivoAnexo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void copiar(InputStream origem, OutputStream destino) {
        int byteTransferencia = 0;
        byte tamanhoMaximo[] = new byte[1024 * 10];
        try {
            while((byteTransferencia = origem.read()) >= 0){
                destino.write(tamanhoMaximo, 0, byteTransferencia);
                
            }
        } catch (IOException ex) {
            Logger.getLogger(ArquivoAnexo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
