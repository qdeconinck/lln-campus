package be.ac.ucl.lfsab1509.llncampus.activity;


import java.io.File;

import be.ac.ucl.lfsab1509.llncampus.ExternalAppUtility;
import be.ac.ucl.lfsab1509.llncampus.LLNCampus;
import be.ac.ucl.lfsab1509.llncampus.R;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

/**
 * Class that will be executed when starting the application.
 * Related with the XML main_title.xml
 *
 */
public class MainTitle extends LLNCampusActivity implements OnClickListener {

	// Defining the URL used in the code
	public final String ICAMPUS_URL="https://www.uclouvain.be/cnx_icampus.html";
	public final String MOODLE_URL="https://www.uclouvain.be/cnx_moodle.html";
	public final String BUREAU_UCL_URL="http://www.uclouvain.be/onglet_bureau.html?";
	public final String MAP_NAME="plan_lln.pdf";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_title);
        
        setListeners(); 
		getActionBar().setDisplayHomeAsUpEnabled(false);

        new Thread(new Runnable() {
        	public void run(){
        		LLNCampus.copyAssets();
        	}
        }).start();
    }
    
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
       super.onWindowFocusChanged(hasFocus);
       //get/set the size here.
       /** Afin d'avoir une largeur ou une hauteur optimale **/
      
       GridLayout gl = (GridLayout) findViewById(R.id.maingridlayout);
       Button buttontmp;

       //Stretch buttons
       int idealChildWidth = (int) (gl.getWidth() / gl.getColumnCount());
       int idealChildHeight = (int) ((gl.getHeight()) / gl.getRowCount());
       for (int i = 0; i < gl.getChildCount(); i++) {
    	   buttontmp = (Button) gl.getChildAt(i);
           buttontmp.setWidth(idealChildWidth);
           buttontmp.setHeight(idealChildHeight);
           Drawable[] drawables = buttontmp.getCompoundDrawables();
           Drawable d;
           for (int j=0; j < drawables.length; j++) {
        	   if ((d = drawables[j]) != null) {
        		   d.setBounds(0, 0, 
        				   (int) (d.getIntrinsicWidth() * 0.9),  
        				   (int) (d.getIntrinsicHeight() * 0.9));
                   ScaleDrawable sd = new ScaleDrawable(d, 0, (float) 1, (float) 1);
                   switch (j) {
                   case 0 : 
                  		buttontmp.setCompoundDrawables(sd.getDrawable(), null, null, null);
                  		break;
                   case 1 : 
                  		buttontmp.setCompoundDrawables(null, sd.getDrawable(), null, null);
                  		break;
                   case 2 : 
                  		buttontmp.setCompoundDrawables(null, null, sd.getDrawable(),  null);
                  		break;
                   case 3 : 
                  		buttontmp.setCompoundDrawables(null, null, null, sd.getDrawable());
                  		break;
                  	}

        	   }
           }
       }
    }

    //  buttons defined in the XML file
    private void setListeners() {
        View myVisitsButton = findViewById(R.id.button_loisirs);
        myVisitsButton.setOnClickListener(this);
        View studyButton = findViewById(R.id.button_horaire);
        studyButton.setOnClickListener(this);
        View auditButton = findViewById(R.id.button_auditoire);
        auditButton.setOnClickListener(this);
        View libraryButton = findViewById(R.id.button_bibliotheque);
        libraryButton.setOnClickListener(this);
        View planButton = findViewById(R.id.button_map);
        planButton.setOnClickListener(this);
        View aboutButton = findViewById(R.id.button_about);
        aboutButton.setOnClickListener(this);
        View iCampusButton = findViewById(R.id.button_icampus);
        iCampusButton.setOnClickListener(this);
        View moodleButton = findViewById(R.id.button_moodle);
        moodleButton.setOnClickListener(this);
        View bureauButton = findViewById(R.id.button_bureau);
        bureauButton.setOnClickListener(this);
    }
    
    // defines the action for each button
	public void onClick(View v) {
		Intent intent;
		switch (v.getId()) {
		case R.id.button_loisirs:
			intent = new Intent(this, LoisirsActivity.class);
			startActivity(intent);
			break;
		case R.id.button_horaire:
			intent = new Intent(this, HoraireActivity.class);
			startActivity(intent);
			break;
		case R.id.button_auditoire:
			intent = new Intent(this, AuditoriumActivity.class);
			startActivity(intent);
			break;
		case R.id.button_bibliotheque:
			intent = new Intent(this, BibliothequesActivity.class);
			startActivity(intent);
			break;	
		case R.id.button_map:
			/*
			intent = new Intent(Intent.ACTION_VIEW);
			Uri uri = Uri.parse("file:///android_asset/plan_2007recto2.png");
			intent.setData(uri);
			*/
			
			// Uses the tablet's default PDF reader. Starts MapActivity if none is found.
			try
			{
			 Intent intentUrl = new Intent(Intent.ACTION_VIEW);
			 
			 
			 Uri url = Uri.fromFile(new File("/" + Environment.getExternalStorageDirectory().getPath() + "/" + LLNCampus.LLNREPOSITORY + "/" +MAP_NAME));
			 intentUrl.setDataAndType(url, "application/pdf");
			 intentUrl.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			 startActivity(intentUrl);
			}
			catch (ActivityNotFoundException e)
			{
			 Toast.makeText(this, "Pas de lecteur de PDF installe: tentative de connection par le net...", Toast.LENGTH_LONG).show();
			 intent = new Intent(this, MapActivity.class);
			 startActivity(intent);
			}
			
			break;	
		case R.id.button_about:
			intent = new Intent(this, AboutActivity.class);
			startActivity(intent);
			break;
		case R.id.button_icampus:
			ExternalAppUtility.openBrowser(MainTitle.this, ICAMPUS_URL);
			break;
		case R.id.button_moodle:
			ExternalAppUtility.openBrowser(MainTitle.this, MOODLE_URL);
			break;
		case R.id.button_bureau:
			ExternalAppUtility.openBrowser(MainTitle.this, BUREAU_UCL_URL);
			break;						
		}
		
	}
}
