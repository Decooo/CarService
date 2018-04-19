package com.serwis.util.imageSettings;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Created by jakub on 19.04.2018.
 */
public class EditAndHistoryButton {

	public static ImageView getImageView(Image imgEdit) {
		ImageView iv = new ImageView();
		iv.setImage(imgEdit);
		iv.setPreserveRatio(true);
		iv.setSmooth(true);
		iv.setCache(true);
		return iv;
	}

}
