package com.pozasoft.android.osferroviarios.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.pozasoft.android.osferroviarios.R;
import com.pozasoft.android.osferroviarios.activity.Parche;
import com.pozasoft.android.osferroviarios.domain.Constants;
import com.pozasoft.android.osferroviarios.domain.Institucion;

public class InstitucionAdapter extends ArrayAdapter<Institucion> {

	private Activity activity;
	private List<Institucion> items;
	private int row;
	private Institucion objBean;
	private List<String> contactosElegidos;

	public InstitucionAdapter(Activity act, int row, List<Institucion> items) {
		super(act, row, items);		
		this.activity = act;
		this.row = row;
		this.items = items;

	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View view = convertView;
		ViewHolder holder;
		if (view == null) {
			LayoutInflater inflater = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(row, null);

			holder = new ViewHolder();
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}

		if ((items == null) || ((position + 1) > items.size()))
			return view;

		objBean = items.get(position);

		holder.tvname     = (TextView) view.findViewById(R.id.tvname);
		holder.tvPhoneNo  = (TextView) view.findViewById(R.id.tvphone);


		try {
			if (holder.tvname != null && null != objBean.getName() && objBean.getName().trim().length() > 0) {
				holder.tvname.setText(objBean.getName());
			}
			if (holder.tvPhoneNo != null && null != objBean.getPhone() && objBean.getPhone().trim().length() > 0){
				holder.tvPhoneNo.setText(Parche.especialidad == Constants.ID_TODOS_BARRIOS ?
						objBean.getEspecialidadName() : objBean.getAddress());
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return view;
	}

	public class ViewHolder {
		public TextView tvname, tvPhoneNo;
	}

}
