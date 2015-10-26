package com.peg.android.pbj.ui.view;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.LinearLayout;

import com.peg.android.pbj.service.DeviceInfo;

public class BarraPaginas extends LinearLayout {

	private final static int SEPARACION_BOTONES  = 10;
	private List<Button> buttons;
	int anchoBoton;
	int screenWidth;
	
	public BarraPaginas(Context context, AttributeSet attrs) {
		super(context, attrs);		
		
		setOrientation(LinearLayout.HORIZONTAL);
		setBackgroundColor(Color.GRAY);
		screenWidth = DeviceInfo.getScreenWidth(context);
		anchoBoton  = (screenWidth - 6 * SEPARACION_BOTONES) / 5;
		
		buttons = new ArrayList<Button>();
		for(int i = 0; i < 5 ; i++){
			Button b = new Button(context);
			buttons.add(b);
			b.setText("" + i);
			addView(b);
		}
	}

    @Override
    public void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {
  		  setMeasuredDimension(screenWidth,50);
  		for(int i = 0; i < 5 ; i++){
			Button b = buttons.get(i);
			android.view.ViewGroup.LayoutParams layoutParams = b.getLayoutParams();
			layoutParams.height = 50;
			layoutParams.width  = anchoBoton;
			b.setLayoutParams(layoutParams);
			b.requestLayout();
		}
    }
    
}
