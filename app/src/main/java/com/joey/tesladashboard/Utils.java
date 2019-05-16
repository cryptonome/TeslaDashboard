package com.joey.tesladashboard;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.joey.tesladashboard.activities.LoginActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Utils {
    private static final String TAG = Utils.class.getSimpleName();
    public static final int ANIMATION_TYPE_TRANSLATION = 0;
    public static final int ANIMATION_TYPE_FADE = 1;

    private static CustomProgressDialog customProgressDialog;

    public static FragmentTransaction setAnimations(FragmentTransaction originalFragmentTransaction, int animationType){
        switch (animationType){
            case ANIMATION_TYPE_TRANSLATION:
                originalFragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
                return originalFragmentTransaction;
            case ANIMATION_TYPE_FADE:
                originalFragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out);
                return originalFragmentTransaction;

            default:
                return originalFragmentTransaction;
        }
    }

    public static void setGridViewHeightBasedOnChildren(GridView gridView, int columns) {
        ListAdapter listAdapter = gridView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        int items = listAdapter.getCount();
        int rows = 0;

        View listItem = listAdapter.getView(0, null, gridView);
        listItem.measure(0, 0);
        totalHeight = listItem.getMeasuredHeight();

        float x = 1;
        if( items > columns ){
            x = items/columns;
            rows = (int) (x + 1);
            totalHeight *= rows;
        }

        ViewGroup.LayoutParams params = gridView.getLayoutParams();
        params.height = totalHeight;
        gridView.setLayoutParams(params);
    }

    public static void justifyListViewHeightBasedOnChildren (ListView listView) {
        ListAdapter adapter = listView.getAdapter();
        if (adapter == null) {
            return;
        }

        ViewGroup vg = listView;
        int totalHeight = 0;
        for (int i = 0; i < adapter.getCount(); i++) {
            View listItem = adapter.getView(i, null, vg);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams par = listView.getLayoutParams();
        par.height = (int)(1.5*totalHeight + (listView.getDividerHeight() * (adapter.getCount() - 1)));
        listView.setLayoutParams(par);
        listView.requestLayout();
    }

    public static void setGridViewWidthBasedOnChildren(GridView gridView) {
        ListAdapter gridViewAdapter = gridView.getAdapter();
        if (gridViewAdapter == null) {
            return;
        }

        ViewGroup vg = gridView;
        int totalWidth = 0;
        for (int i = 0; i < gridViewAdapter.getCount(); i++) {
            View gridItem = gridViewAdapter.getView(i, null, vg);
            gridItem.measure(0, 0);
            totalWidth += gridItem.getMeasuredHeight(); //Hack: use getMeasuredHeight instead of getMeasuredWidth as each grid item is square anyway, becauase of ScrollingTextviews variable width
        }

        ViewGroup.LayoutParams params = gridView.getLayoutParams();
        params.width = totalWidth;
        gridView.setLayoutParams(params);
    }

    public static String getDateString(long timestamp){
        String dateString = "";
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);
        dateString = day + "/" + month + "/" + year;

        return dateString;
    }

    private static SimpleDateFormat simpleDateFormatDateHoursMinute;
    public static String getTimeStringDateHoursMinutes(long timestamp){
        String timeString = "";
        /*Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp);
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        String amPM = "";
        if(calendar.get(Calendar.AM_PM) == Calendar.PM){
            amPM = "PM";
        }else{
            amPM = "AM";
        }
        timeString = hour + ":" + minute + ":" + second + " " + amPM;*/
        if(simpleDateFormatDateHoursMinute == null) {
            simpleDateFormatDateHoursMinute = new SimpleDateFormat("dd/MM/yy  h:mm a", new Locale("en"));
        }
        timeString = simpleDateFormatDateHoursMinute.format(timestamp);
        return timeString;
    }

    private static SimpleDateFormat simpleDateFormatHoursMinute;
    public static String getTimeStringHoursMinutes(long timestamp){
        String timeString = "";
        /*Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp);
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        String amPM = "";
        if(calendar.get(Calendar.AM_PM) == Calendar.PM){
            amPM = "PM";
        }else{
            amPM = "AM";
        }
        timeString = hour + ":" + minute + ":" + second + " " + amPM;*/
        if(simpleDateFormatHoursMinute == null) {
            simpleDateFormatHoursMinute = new SimpleDateFormat("h:mm a", new Locale("en"));
        }
        timeString = simpleDateFormatHoursMinute.format(timestamp);
        return timeString;
    }

    private static SimpleDateFormat simpleDateFormatServer; //2019-04-14 10:29:23
    public static long getTimestamp(String serverTimestamp){
        long timestamp = 0;
        if(simpleDateFormatServer == null) {
            simpleDateFormatServer = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        }
        try{
            Date date = simpleDateFormatServer.parse(serverTimestamp);
            timestamp = date.getTime();
        }catch (ParseException e){
            Log.d(TAG, "Exception: " + e.getMessage());
        }

        return timestamp;
    }

    public static boolean validateInputs(EditText... editTexts){
        boolean inputsValid = true;

        for (EditText editText:editTexts) {
            if(editText == null || editText.getText().toString() == null || editText.getText().toString().length() < 1){
                inputsValid = false;
                YoYo.with(Techniques.Shake)
                        .duration(700)
                        .repeat(1)
                        .playOn(editText);
            }
        }

        return inputsValid;
    }

    public static boolean validateInputsWithoutYoyo(EditText... editTexts){
        boolean inputsValid = true;

        for (EditText editText:editTexts) {
            if(editText == null || editText.getText().toString() == null || editText.getText().toString().length() < 1){
                inputsValid = false;
            }
        }

        return inputsValid;
    }

    public static String formatCurrency(int amount){
        String formattedAmount = "";

        formattedAmount = formattedAmount.concat("$ ").concat(""+amount);

        return formattedAmount;
    }

    public static class InternetChecker extends AsyncTask<Void, Void, Boolean> {

        private OnConnectionCallback onConnectionCallback;
        private Context context;

        public InternetChecker(Context context, OnConnectionCallback onConnectionCallback) {
            super();
            this.onConnectionCallback = onConnectionCallback;
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            if (context == null)
                return false;

            try {
                InetAddress ipAddr = InetAddress.getByName("google.com");
                //You can replace it with your name
                return !ipAddr.equals("");

            } catch (Exception e) {
                Utils.log(TAG, "Exception: " + e.getMessage());
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean b) {
            super.onPostExecute(b);

            if (b) {
                onConnectionCallback.onConnectionSuccess();
            } else {
                String msg = "No Internet Connection";
                if (context == null)
                    msg = "Context is null";
                onConnectionCallback.onConnectionFail(msg);
            }

        }

        public interface OnConnectionCallback {
            void onConnectionSuccess();
            void onConnectionFail(String errorMsg);
        }
    }

    public static void showLoading(Context context){
        customProgressDialog = CustomProgressDialog.show(context, "", "");
    }

    public static void dismissLoading(){
        if(customProgressDialog != null && customProgressDialog.isShowing()){
            customProgressDialog.dismiss();
        }
    }

    public static Bitmap resizeBitmapByDimensions(Bitmap bitmap, int width, int height, boolean recycle) {
        if (width == bitmap.getWidth() && height == bitmap.getHeight())
            return bitmap;

        //Bitmap target = Bitmap.createScaledBitmap(bitmap, width, height, false);

        float scale = Math.min(((float)height / bitmap.getWidth()), ((float)width / bitmap.getHeight()));
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);
        Bitmap target = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);

        if (recycle) bitmap.recycle();
        return target;
    }

    public static Bitmap resizeBitmapByScale(Bitmap bitmap, float scale, boolean recycle) {
        //Bitmap target = Bitmap.createScaledBitmap(pickedBitmap, 120, 120, false);
        int width = Math.round(bitmap.getWidth() * scale);
        int height = Math.round(bitmap.getHeight() * scale);
        if (width == bitmap.getWidth() && height == bitmap.getHeight())
            return bitmap;
        Bitmap target = Bitmap.createBitmap(width, height, getConfig(bitmap));
        Canvas canvas = new Canvas(target);
        canvas.scale(scale, scale);
        Paint paint = new Paint(Paint.FILTER_BITMAP_FLAG | Paint.DITHER_FLAG);
        canvas.drawBitmap(bitmap, 0, 0, paint);
        if (recycle) bitmap.recycle();
        return target;
    }

    private static Bitmap.Config getConfig(Bitmap bitmap) {
        Bitmap.Config config = bitmap.getConfig();
        if (config == null) {
            config = Bitmap.Config.ARGB_8888;
        }
        return config;
    }

    public static String getMD5(String data){
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(data.getBytes("UTF-8"));
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        } catch(UnsupportedEncodingException ex){
        }
        return null;
    }

    public static void showToast(Context context, String text, boolean longDuration){
        if(context != null){
            if(longDuration){
                Toast.makeText(context, text, Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
            }
        }
    }

    public static String getString(Context context, int resID){
        if(context != null){
            return context.getResources().getString(resID);
        }else {
            return "";
        }
    }

    public static String getStringExtraInt(Context context, int resID, Integer... params){
        if(context != null){
            if(params != null){
                int length = params.length;
                switch (length){
                    case 1:
                        return context.getResources().getString(resID, params[0]);
                    case 2:
                        return context.getResources().getString(resID, params[0], params[1]);
                    case 3:
                        return context.getResources().getString(resID, params[0], params[1], params[2]);
                    case 4:
                        return context.getResources().getString(resID, params[0], params[1], params[2], params[3]);
                    default:
                        return context.getResources().getString(resID);
                }
            }else{
                return context.getResources().getString(resID);
            }
        }else {
            return "";
        }
    }

    public static String getStringExtraText(Context context, int resID, String... params){
        if(context != null){
            if(params != null){
                int length = params.length;
                switch (length){
                    case 1:
                        return context.getResources().getString(resID, params[0]);
                    case 2:
                        return context.getResources().getString(resID, params[0], params[1]);
                    case 3:
                        return context.getResources().getString(resID, params[0], params[1], params[2]);
                    case 4:
                        return context.getResources().getString(resID, params[0], params[1], params[2], params[3]);
                    default:
                        return context.getResources().getString(resID);
                }
            }else{
                return context.getResources().getString(resID);
            }
        }else {
            return "";
        }
    }

    public static void log(String tag, String message){
        Log.d(tag, message);
    }

    public static void hideKeyboard(View view, Context context){
        if (view != null) {
            //view.clearFocus();
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            //imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
        }
    }

    public static void showLoginPopup(final Activity context){
        //show popup tell user to login
        AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle(context.getResources().getString(R.string.session_expired))
                .setMessage(context.getResources().getString(R.string.login_again))
                .setPositiveButton(context.getResources().getString(R.string.login), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MySettings.setActiveUser(null);
                        MySettings.setAppFirstStart(true);

                        Intent loginIntent = new Intent(context, LoginActivity.class);
                        context.startActivity(loginIntent);
                        context.finish();
                    }
                }).setCancelable(false).show();
    }

    public static byte[] fullyReadFileToBytes(File f) throws IOException {
        int size = (int) f.length();
        byte bytes[] = new byte[size];
        byte tmpBuff[] = new byte[size];
        FileInputStream fis= new FileInputStream(f);;
        try {

            int read = fis.read(bytes, 0, size);
            if (read < size) {
                int remain = size - read;
                while (remain > 0) {
                    read = fis.read(tmpBuff, 0, remain);
                    System.arraycopy(tmpBuff, 0, bytes, size - remain, read);
                    remain -= read;
                }
            }
        }  catch (IOException e){
            throw e;
        } finally {
            fis.close();
        }

        return bytes;
    }

    public static Gson getGson(){
        return new GsonBuilder()
                .registerTypeAdapter(Boolean.class, booleanAsIntAdapter)
                .registerTypeAdapter(boolean.class, booleanAsIntAdapter)
                .create();
    }
    private static final TypeAdapter<Boolean> booleanAsIntAdapter = new TypeAdapter<Boolean>() {
        @Override public void write(JsonWriter out, Boolean value) throws IOException {
            if (value == null) {
                out.nullValue();
            } else {
                out.value(value);
            }
        }
        @Override public Boolean read(JsonReader in) throws IOException {
            JsonToken peek = in.peek();
            switch (peek) {
                case BOOLEAN:
                    return in.nextBoolean();
                case NULL:
                    in.nextNull();
                    return null;
                case NUMBER:
                    return in.nextInt() != 0;
                case STRING:
                    return Boolean.parseBoolean(in.nextString());
                default:
                    throw new IllegalStateException("Expected BOOLEAN or NUMBER but was " + peek);
            }
        }
    };
}
