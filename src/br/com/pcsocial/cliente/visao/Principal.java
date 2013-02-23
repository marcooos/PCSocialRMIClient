package br.com.pcsocial.cliente.visao;

import javax.swing.UIManager;

import org.pushingpixels.substance.api.skin.SubstanceOfficeBlack2007LookAndFeel;

public class Principal {
	
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
			public void run() {
            	 try {
                     UIManager.setLookAndFeel(new SubstanceOfficeBlack2007LookAndFeel());
                   } catch (Exception e) {
                     System.out.println("Substance Graphite failed to initialize");
                   }
            	ValidarLoginUI vlUI = new ValidarLoginUI();
            	vlUI.createAndShowGUI();
            }
        });

    }

}
