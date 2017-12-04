package com.passeride.fxtest;

import javafx.application.Platform;

public class PlatformHelper {
	
	/*		
		Runnable r = new Runnable() {

			@Override
			public void run() {

		        
			}
			
		};
	 * 
	 * 
	 * PlatformHelper.run(r);
	 */
    public static void run(Runnable treatment) {
        if(treatment == null) throw new IllegalArgumentException("The treatment to perform can not be null");
 
        if(Platform.isFxApplicationThread()) treatment.run();
        else Platform.runLater(treatment);
    }
}
