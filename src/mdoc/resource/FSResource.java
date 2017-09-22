package mdoc.resource;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

/**
 * Armazena todas as imagens
 * 
 * 
 * @author bbreder
 */
public abstract class FSResource {

	/** Mapa de imagens */
	private static final Map<String, BufferedImage> images = new HashMap<String, BufferedImage>();

	/**
	 * Construtor
	 */
	private FSResource() {
	}

	/**
	 * Imagem de validação
	 * 
	 * @return image
	 */
	public static BufferedImage getHelp() {
		return getImage("help.png");
	}

	/**
	 * Imagem de validação
	 * 
	 * @return image
	 */
	public static BufferedImage getCopy() {
		return getImage("copy.png");
	}

	/**
	 * Imagem de validação
	 * 
	 * @return image
	 */
	public static BufferedImage getMove() {
		return getImage("move.png");
	}

	/**
	 * Imagem de validação
	 * 
	 * @return image
	 */
	public static BufferedImage getEdit() {
		return getImage("edit.png");
	}

	/**
	 * Imagem de validação
	 * 
	 * @return image
	 */
	public static BufferedImage getPaste() {
		return getImage("paste.png");
	}

	/**
	 * Imagem de validação
	 * 
	 * @return image
	 */
	public static BufferedImage getDuplicate() {
		return getImage("duplicate.png");
	}

	/**
	 * Imagem de validação
	 * 
	 * @return image
	 */
	public static BufferedImage getDel() {
		return getImage("del.png");
	}

	/**
	 * Imagem de validação
	 * 
	 * @return image
	 */
	public static BufferedImage getRename() {
		return getImage("rename.png");
	}

	/**
	 * Imagem de validação
	 * 
	 * @return image
	 */
	public static BufferedImage getSave() {
		return getImage("save.png");
	}

	/**
	 * Imagem de validação
	 * 
	 * @return image
	 */
	public static BufferedImage getPrint() {
		return getImage("print.png");
	}

	/**
	 * Imagem de validação
	 * 
	 * @return image
	 */
	public static BufferedImage getExport() {
		return getImage("export.png");
	}

	/**
	 * Imagem de validação
	 * 
	 * @return image
	 */
	public static BufferedImage getHome() {
		return getImage("home.png");
	}

	/**
	 * Imagem de validação
	 * 
	 * @return image
	 */
	public static BufferedImage getLogo() {
		return getImage("logo.jpg");
	}

	/**
	 * Imagem de validação
	 * 
	 * @return image
	 */
	public static BufferedImage getFileMenu() {
		return getImage("file_menu.png");
	}

	/**
	 * Imagem de validação
	 * 
	 * @return image
	 */
	public static BufferedImage getYes() {
		return getImage("yes.png");
	}

	/**
	 * Imagem de validação
	 * 
	 * @return image
	 */
	public static BufferedImage getNo() {
		return getImage("no.png");
	}

	/**
	 * Imagem de validação
	 * 
	 * @return image
	 */
	public static BufferedImage getCancel() {
		return getImage("cancel.png");
	}

	/**
	 * Imagem de validação
	 * 
	 * @return image
	 */
	public static BufferedImage getQuit() {
		return getImage("quit.png");
	}

	/**
	 * Imagem de validação
	 * 
	 * @return image
	 */
	public static BufferedImage getSignout() {
		return getImage("signout.png");
	}

	/**
	 * Imagem de validação
	 * 
	 * @return image
	 */
	public static BufferedImage getLoading() {
		return getImage("loading.gif");
	}

	/**
	 * Imagem de validação
	 * 
	 * @return image
	 */
	public static BufferedImage getFolderOpen() {
		return getImage("folder_open.png");
	}

	/**
	 * Imagem de validação
	 * 
	 * @return image
	 */
	public static BufferedImage getFolderClose() {
		return getImage("folder_close.png");
	}

	/**
	 * Imagem de trayicon
	 * 
	 * @return image
	 */
	public static BufferedImage getWord() {
		return getImage("word.png");
	}

	/**
	 * Imagem de suma
	 * 
	 * @return image
	 */
	public static BufferedImage getTrayicon() {
		return getImage("trayicon.png");
	}

	/**
	 * Retorna a imagem
	 * 
	 * @param name
	 * @return imagem
	 */
	public static BufferedImage getImage(String name) {
		BufferedImage image = images.get(name);
		if (image == null) {
			image = ImageUtil.load("/mdoc/resource/" + name);
			images.put(name, image);
		}
		return image;
	}

}
