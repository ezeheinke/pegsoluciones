package com.peg.android.pbj.ui.activity;

import java.util.ArrayList;
import java.util.Hashtable;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.TabActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabContentFactory;
import android.widget.TextView;

import com.peg.android.pbj.R;
import com.peg.android.pbj.domain.Banner;
import com.peg.android.pbj.domain.Noticia;
import com.peg.android.pbj.domain.PBJApplication;
import com.peg.android.pbj.domain.Strings;
import com.peg.android.pbj.service.BannerService;
import com.peg.android.pbj.service.MenuExecutor;
import com.peg.android.pbj.service.NoticiasService;
import com.peg.android.pbj.service.ServiceError;
import com.peg.android.pbj.service.ServiceListener;


public class TabbedListListActivity extends TabActivity implements OnTabChangeListener {

    private static final String CATEGORIA1  = "HOME";
    private static final String CATEGORIA2  = "FUTBOL";
    private static final String CATEGORIA3  = "BASKET";
    private static final String CATEGORIA4  = "VOLEY";
    private static final String CATEGORIA5  = "PASES";
    private static final String CATEGORIA6  = "FEMENINO";
    private static final String CATEGORIA7  = "INTERNACIONAL";
    private static final String CATEGORIA8  = "INFERIORES";
    private static final String CATEGORIA9  = "INSTITUCIONAL";
    private static final String CATEGORIA10 = "ENTRENAMIENTOS";
    private static final String CATEGORIA11 = "SELECCION";
    private static final BannerService bannerService = new BannerService();
    private static final Hashtable<String, String> categorias;
    
    static{
    	categorias = new Hashtable<String, String>();    	
    	categorias.put(CATEGORIA1, 	Strings.CATEGORIA_RECIENTES);
    	categorias.put(CATEGORIA2, 	Strings.FUTBOL);
    	categorias.put(CATEGORIA3, 	Strings.BASKET);
    	categorias.put(CATEGORIA4, 	Strings.VOLEY);
    	categorias.put(CATEGORIA5,	Strings.MERCADO_DE_PASES);
    	categorias.put(CATEGORIA6,  Strings.FEMENINO);
    	//categorias.put(CATEGORIA7,  Strings.INTERNACIONAL);
    	categorias.put(CATEGORIA8,  Strings.INFERIORES);
    	categorias.put(CATEGORIA9,  Strings.INSTITUCIONAL);
    	//categorias.put(CATEGORIA10, Strings.ENTRENAMIENTOS);
    	//categorias.put(CATEGORIA11, Strings.SELECCION); 
    }
    
    private LinearLayout 		listView;
    private NoticiasService 	noticiasService;
    private TabHost 			tabHost;
    private ScrollView 			scrollView;
    private ImageView 			banner;      
    private ArrayList<Banner>	banners;
	private ProgressDialog 		dialog;
    private int 				bannerPos;
	private Button 				currentPageButton;
	private Button 				firstPageButton;
	private String 				tabName;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);		
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);		
		setContentView(R.layout.lhome);
        
		menuExecutor = new MenuExecutor(this);
		noticiasService = new NoticiasService();		
    	configurePageButtons();
    	
    	
    	PBJApplication.banner = (ImageView) findViewById(R.id.banner);    	
    	dialog = new ProgressDialog(this);
    	dialog.setTitle("Cargando");
    	dialog.setMessage("Espere por favor...");
    	dialog.setCancelable(false);        	
    	
    	banner = (ImageView) findViewById(R.id.banner);
    	banner.setOnClickListener(new OnClickListener() {				
			@Override
			public void onClick(View v) {
				Intent browserIntent = new Intent(Intent.ACTION_VIEW, 
						Uri.parse(banners.get(bannerPos).getLink()));
				startActivity(browserIntent);
			}
		});
    	
    	bannerService.getBanners(new ServiceListener() {        		
    		public void onError(ServiceError e) {

    		}
    		
    		@SuppressWarnings("unchecked")
			public void onComplete(Object response) {
    			banners = (ArrayList<Banner>) response;
    			bannerPos = -1;
    			new Thread() {
    	            public void run() {     
    	            	while(true){
    		            	try {
    							Thread.sleep(4000);
    							bannerPos = ++bannerPos % banners.size();
    				            messagHandler.sendMessage(Message.obtain());        				            
    						} catch (InterruptedException e) {					
    							e.printStackTrace();
    						}	           
    	            	}
    	            }
    	        }.start();
    		}
    	});
    	
    	scrollView = (ScrollView) findViewById(R.id.scrollnoticias);        	
    	tabHost = getTabHost();
    	tabHost.setOnTabChangedListener(this);        	
    	listView = (LinearLayout) findViewById(R.id.list1);    
  	
    	TabContentFactory tabContentFactory = new TabContentFactory() {        		
    		public View createTabContent(String arg0) {        			
    			return listView;        			
    		}        		
    	};
    	
		tabHost.addTab(tabHost.newTabSpec(CATEGORIA1).setIndicator(CATEGORIA1).setContent(tabContentFactory));        	
		tabHost.addTab(tabHost.newTabSpec(CATEGORIA2).setIndicator(CATEGORIA2).setContent(tabContentFactory));        	
    	tabHost.addTab(tabHost.newTabSpec(CATEGORIA3).setIndicator(CATEGORIA3).setContent(tabContentFactory));        	
    	tabHost.addTab(tabHost.newTabSpec(CATEGORIA4).setIndicator(CATEGORIA4).setContent(tabContentFactory));        	
    	tabHost.addTab(tabHost.newTabSpec(CATEGORIA5).setIndicator(CATEGORIA5).setContent(tabContentFactory));            	
		tabHost.addTab(tabHost.newTabSpec(CATEGORIA6).setIndicator(CATEGORIA6).setContent(tabContentFactory));        	
		//tabHost.addTab(tabHost.newTabSpec(CATEGORIA7).setIndicator(CATEGORIA7).setContent(tabContentFactory));        	
    	tabHost.addTab(tabHost.newTabSpec(CATEGORIA8).setIndicator(CATEGORIA8).setContent(tabContentFactory));        	
    	tabHost.addTab(tabHost.newTabSpec(CATEGORIA9).setIndicator(CATEGORIA9).setContent(tabContentFactory));        	
    	//tabHost.addTab(tabHost.newTabSpec(CATEGORIA10).setIndicator(CATEGORIA10).setContent(tabContentFactory));
    	//tabHost.addTab(tabHost.newTabSpec(CATEGORIA11).setIndicator(CATEGORIA11).setContent(tabContentFactory));
    	
    	Display display = getWindowManager().getDefaultDisplay();
    	
    	for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
            View childAt = tabHost.getTabWidget().getChildAt(i);
			childAt.getLayoutParams().width =  (int) (0.25 * display.getWidth());
        }
    	
    }

    private void configurePageButtons() {
		OnClickListener listener = new OnClickListener() {						

			@Override
			public void onClick(View v) {
				currentPageButton.setEnabled(true);
				currentPageButton = (Button) v;
				currentPageButton.setEnabled(false);
				pageButtonAction(((Button)v).getText().toString());
			}
		};
		
		ArrayList<Button> pageButtons = new ArrayList<Button>();
		
		View v = findViewById(R.id.pag1);
		currentPageButton = (Button)v;
		firstPageButton = currentPageButton;
		v.setOnClickListener(listener);
		
		v = findViewById(R.id.pag2);
		v.setOnClickListener(listener);
		
		v = findViewById(R.id.pag3);
		v.setOnClickListener(listener);
		
		v = findViewById(R.id.pag4);
		v.setOnClickListener(listener);
		
		v = findViewById(R.id.pag5);
		v.setOnClickListener(listener);
	}

    private void pageButtonAction(String page){
    	dialog.show();
		noticiasService.getNoticias(new ServiceListener() {			
			public void onError(ServiceError e) {
				android.content.DialogInterface.OnClickListener listenerOnClick = new android.content.DialogInterface.OnClickListener() {				
					public void onClick(DialogInterface dialog, int which) {
											
					}
				};
				
				dialog.cancel();
				AlertDialog.Builder builder = new AlertDialog.Builder(TabbedListListActivity.this);
				
				builder.setTitle("Error")
						.setMessage("No se puedo acceder a las noticias, intentelo mas tarde.")
						.setNeutralButton("OK", listenerOnClick)
						.setCancelable(true);  
				
				builder.create().show();
		    	
			}
			
			@SuppressWarnings("unchecked")
			public void onComplete(Object response) {
				try{
			    	 ArrayList<Noticia> mensajes = (ArrayList<Noticia>) response;
				     addToList(mensajes);
				}catch(Exception e){
					dialog.cancel();					
				}				
			}
		}, categorias.get(tabName), page);    	
    }
    
	public void onTabChanged(String name) {    	    	
		tabName = name;
		pageButtonAction("1");
		currentPageButton.setEnabled(true);
		currentPageButton = firstPageButton;
		currentPageButton.setEnabled(false);
	}

	protected void addToList(ArrayList<Noticia> noticias) {
	
		listView.removeViews(0,listView.getChildCount() - 1);
		
		for(int i = 0 ; i < noticias.size() ; i++){
			final Noticia n = noticias.get(i);
	    	LayoutInflater inflater = (LayoutInflater)TabbedListListActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    	View v = (View) inflater.inflate(R.layout.noticia, null);
	    	
	    	v.setOnClickListener(new OnClickListener() {				
	    		public void onClick(View v) {
	    			Intent myIntent = new Intent(TabbedListListActivity.this, DetalleActivity.class);
	    			myIntent.putExtra("noticia", n);
					startActivity(myIntent);					
				}
	    	});	    	    	
	    	 
	    	TextView t = (TextView) v.findViewById(R.id.titulo);
	    	t.setText(n.getTitulo());
	    	
	    	t = (TextView) v.findViewById(R.id.resumen);
	    	t.setText(n.getResumen());
	    	
	    	t = (TextView) v.findViewById(R.id.redactor);
	    	t.setText(n.getAutor());
	    	
	    	t = (TextView) v.findViewById(R.id.cantidad_comentarios);
	    	t.setText("Comentarios: " + n.getCantComentarios());	    	
	    	
	    	listView.addView(v,listView.getChildCount() - 1);	
	    	
		    v = (View) inflater.inflate(R.layout.linea, null);		
		    listView.addView(v,listView.getChildCount() - 1);

	     }		
		
		scrollView.scrollTo(0, 0);		
		dialog.cancel();		
	}
	
	private Handler messagHandler = new Handler() {
		public void handleMessage(Message message) {
			Drawable drawable = banners.get(bannerPos).getDrawable();
			banner.setBackgroundDrawable(drawable);
		}
	};
	private MenuExecutor menuExecutor;

	@Override
	
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.menu, menu);
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {		
		return menuExecutor.onClickMenuItem(item);
	}	

	@Override
	protected void onPause() {		
		super.onPause();
		LinearLayout l = (LinearLayout) findViewById(R.id.home);
		l.removeView(PBJApplication.banner);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		if(PBJApplication.banner.getParent() == null){
			LinearLayout l = (LinearLayout) findViewById(R.id.home);
			l.addView(PBJApplication.banner); 
		}
	}
}
