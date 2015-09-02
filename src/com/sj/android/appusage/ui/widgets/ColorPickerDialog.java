package com.sj.android.appusage.ui.widgets;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.widget.ArrayAdapter;

import com.sj.android.appusage.R;
import com.sj.android.appusage.Utility.UsageSharedPrefernceHelper;

public class ColorPickerDialog {
	
	public static int mCurrentThemeColor;
	
	private int mColors[] = new int[]{0xff1ca8f4, 0xFF9C2902,0xFF0F9D58,0xFF708090,0xFFFF69B4};
	
	public interface ColorChangeObserver {
		public void notifyColorChange(int color);
	}
	
	public ColorPickerDialog(Context context) {
		mCurrentThemeColor = UsageSharedPrefernceHelper.getCurrentTheme(context);
	}
	
	ColorChangeObserver mObserver = null;
	
	public void registerColorChangeObserver(ColorChangeObserver observer){
		mObserver = observer;
	}
	
	
	public void show(final Context context){
		AlertDialog.Builder changeThemeBuilder = new AlertDialog.Builder(
				context).setTitle(context.getResources().getString(R.string.string_change_theme))
				.setAdapter(
						new ArrayAdapter<>(context,
								android.R.layout.simple_list_item_1,
								new String[] {
										context.getResources().getString(R.string.string_blue),
										context.getResources().getString(R.string.string_red),
										context.getResources().getString(R.string.string_green),
										context.getResources().getString(R.string.string_grey),
										context.getResources().getString(R.string.string_pink) }),
						new OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								ColorPickerDialog.mCurrentThemeColor = mColors[which];
								UsageSharedPrefernceHelper.setCurrentSelectedTheme(context, mColors[which]);
								if(mObserver != null){
									mObserver.notifyColorChange(mColors[which]);
								}
							}

						});
		AlertDialog themeDialog = changeThemeBuilder.create();
		themeDialog.show();
	}

}
