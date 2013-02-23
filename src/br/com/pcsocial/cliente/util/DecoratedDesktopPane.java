package br.com.pcsocial.cliente.util;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;

public class DecoratedDesktopPane extends JDesktopPane {

	private static final long serialVersionUID = 1L;
	private Image img;

    public DecoratedDesktopPane(String caminho) {
        try {
            //img = ImageIO.read(new File(caminho));
        	img = new ImageIcon(getClass().getResource(caminho)).getImage();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro " + ex.getMessage());
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (img != null) {
            Dimension dimension = this.getSize();
            int x = (int) (dimension.getWidth() - img.getWidth(this)) / 2;
            int y = (int) (dimension.getHeight() - img.getHeight(this)) / 2;

            g.drawImage(img, x, y, img.getWidth(this), img.getHeight(this), this);
        } else {
            g.drawString("Imagem nao encontrada", 50, 50);
        }
    }
}
