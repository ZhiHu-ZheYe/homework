package com.jiajun.edusocial.pic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiajun.edusocial.R;
import com.jiajun.edusocial.pic.BitmapCache.ImageCallback;



public class ImageGridAdapter extends BaseAdapter {

	private TextCallback textcallback = null;
	final String TAG = getClass().getSimpleName();
	Activity act;
	int max;
	List<ImageItem> dataList;
	Map<String, String> map = new HashMap<String, String>();
	BitmapCache cache;
	private Handler mHandler;
	private int selectTotal = 0;
	ImageCallback callback = new ImageCallback() {
		@Override
		public void imageLoad(ImageView imageView, Bitmap bitmap,
				Object... params) {
			if (imageView != null && bitmap != null) {
				String url = (String) params[0];
				if (url != null && url.equals((String) imageView.getTag())) {
					((ImageView) imageView).setImageBitmap(bitmap);
				} else {
//					Log.e(TAG, "callback, bmp not match");
				}
			} else {
//				Log.e(TAG, "callback, bmp null");
			}
		}
	};

	public static interface TextCallback {
		public void onListen(int count);
	}

	public void setTextCallback(TextCallback listener) {
		textcallback = listener;
	}

	public ImageGridAdapter(Activity act, List<ImageItem> list, Handler mHandler,int max) {
		this.act = act;
		dataList = list;
		cache = new BitmapCache();
		this.mHandler = mHandler;
		this.max = max;
	}

	@Override
	public int getCount() {
		int count = 0;
		if (dataList != null) {
			count = dataList.size();
		}
		return count;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	class Holder {
		private ImageView iv;
		private ImageView selected;
		private TextView text;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final Holder holder;

		if (convertView == null) {
			holder = new Holder();
			convertView = View.inflate(act, R.layout.item_image_grid, null);
			holder.iv = (ImageView) convertView.findViewById(R.id.image);
			holder.selected = (ImageView) convertView
					.findViewById(R.id.isselected);
			holder.text = (TextView) convertView
					.findViewById(R.id.item_image_grid_text);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}
		final ImageItem item = dataList.get(position);

		holder.iv.setTag(item.imagePath);
		cache.displayBmp(holder.iv, item.thumbnailPath, item.imagePath,
				callback);
		if (item.isSelected) {
			holder.selected.setVisibility(View.VISIBLE);
			holder.selected.setImageResource(R.drawable.icon_data_select);  
			holder.text.setVisibility(View.INVISIBLE);
			holder.text.setBackgroundResource(R.drawable.bgd_relatly_line);
		} else {
			//这里
			holder.selected.setVisibility(View.GONE);
//			holder.selected.setImageResource(-1);
			holder.text.setVisibility(View.GONE);
//			holder.text.setBackgroundColor(0x00000000);
		}
		holder.iv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String path = dataList.get(position).imagePath;

				if ((Bimp.drr.size() + selectTotal) < max) {
					item.isSelected = !item.isSelected;
					if (item.isSelected) {
						holder.selected.setVisibility(View.VISIBLE);
						holder.selected
								.setImageResource(R.drawable.icon_data_select);
						holder.text.setVisibility(View.INVISIBLE);
						holder.text.setBackgroundResource(R.drawable.bgd_relatly_line);
						selectTotal++;
						if (textcallback != null)
							textcallback.onListen(0);
						map.put(path, path);
						Bimp.max = 0;
						Bimp.act_bool = true;
						Bimp.drr.clear();
						Bimp.bmp.clear();

						Intent intent = new Intent(act,
								PhotoActivity2.class);
								//把你点的是adapter中第几张图片传过去，
								Bitmap bm = BitmapFactory.decodeFile(item.imagePath);
								Bimp.bmp.add(bm);
								Bimp.max = 1;
								Bimp.drr.add(item.imagePath);
								intent.putExtra("ID", position);
								act.startActivityForResult(intent, 78);
					} else if (!item.isSelected) {
						holder.selected.setVisibility(View.GONE);
//						holder.selected.setImageResource(-1);
						holder.text.setVisibility(View.GONE);
//						holder.text.setBackgroundColor(0x00000000);
						selectTotal--;
						if (textcallback != null)
							textcallback.onListen(1);
						map.remove(path);
					}
				} else if ((Bimp.drr.size() + selectTotal) >= max) {
					if (item.isSelected == true) {
						item.isSelected = !item.isSelected;
						//这里
						holder.selected.setVisibility(View.GONE);
//						holder.selected.setImageResource(-1);
						selectTotal--;
						map.remove(path);
						Bimp.max = 0;
						Bimp.act_bool = true;
						Bimp.drr.clear();
						Bimp.bmp.clear();

					} else {
						Message message = Message.obtain(mHandler, 0);
						message.sendToTarget();
					}
				}
//				if (max ==1) {
//					Intent intent = new Intent(act,
//					PhotoActivity2.class);
//					//把你点的是adapter中第几张图片传过去，
//					Bitmap bm = BitmapFactory.decodeFile(item.imagePath);
//					Bimp.bmp.add(bm);
//					Bimp.max = 1;
//					Bimp.drr.add(item.imagePath);
//					intent.putExtra("ID", position);
//					act.startActivity(intent);
//				}else {
//					
//				}
			}

		});

		return convertView;
	}
}
