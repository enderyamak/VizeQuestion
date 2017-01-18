package com.example.ecir.vizequestion;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DrawableUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;
import java.util.StringTokenizer;
//version1.1

public class MainActivity extends AppCompatActivity {
    int[] images;
    int[] imagePlace = {0, 1, 2, 3, 4, 5, 6, 7, 8};
    ImageView[] views;
    Button btn ;
    ImageView firstImage, secondImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageManupilations();
        imageviewManupilations();

        btn = (Button)findViewById(R.id.btn_start);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firstImage = null;
                secondImage = null;
                shuffleNumbers();
                imageAssign();
            }
        });
    }

    public void shuffleNumbers(){
        //Karıştırma işlemi yapılıyor
        Random rnd = new Random();
        int index, temp;
        for(int i = 0; i< imagePlace.length; i++){
            index = rnd.nextInt(imagePlace.length);
            temp = imagePlace[i];
            imagePlace[i] = imagePlace[index];
            imagePlace[index] = temp;
        }

    }

    public void imageAssign(){
        for(int i= 0; i < views.length; i++)
            views[i].setImageResource(images[imagePlace[i]]);

    }


    public void imageManupilations(){

        //image'ler diziye aktarılıyor
        images = new int[9];
        images[0] = R.drawable.img0;
        images[1] = R.drawable.img1;
        images[2] = R.drawable.img2;
        images[3] = R.drawable.img3;
        images[4] = R.drawable.img4;
        images[5] = R.drawable.img5;
        images[6] = R.drawable.img6;
        images[7] = R.drawable.img7;
        images[8] = R.drawable.img8;
    }

    public void imageviewManupilations(){
        //ImageView nesneleri diziye aktarılıyot
        views = new ImageView[9];
        views[0] = (ImageView)findViewById(R.id.res0);
        views[1] = (ImageView)findViewById(R.id.res1);
        views[2] = (ImageView)findViewById(R.id.res2);
        views[3] = (ImageView)findViewById(R.id.res3);
        views[4] = (ImageView)findViewById(R.id.res4);
        views[5] = (ImageView)findViewById(R.id.res5);
        views[6] = (ImageView)findViewById(R.id.res6);
        views[7] = (ImageView)findViewById(R.id.res7);
        views[8] = (ImageView)findViewById(R.id.res8);

        //Imageview nesnelerine default image ve clicklistenerekleniyor
        for(int i= 0; i < views.length; i++){
            views[i].setImageResource(R.drawable.resnull);
            views[i].setOnClickListener(onClick);
        }
    }

    private View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //Eğer ilk image seçilmemişse
            if(firstImage == null){
                firstImage = (ImageView) view;
                return;
            }

            //Eğer ilk image seçilmişse
            if(firstImage != null){
                secondImage = (ImageView) view;
                Drawable tempImage = firstImage.getDrawable();
                firstImage.setImageDrawable(secondImage.getDrawable());
                secondImage.setImageDrawable(tempImage);
                //dizi yerleşimi düzenleniyor
                String firstName = getResources().getResourceName(firstImage.getId());
                String secondName = getResources().getResourceName(secondImage.getId());
                int firstRow = Integer.parseInt(firstName.substring(firstName.length() - 1));
                int secondRow = Integer.parseInt(secondName.substring(secondName.length() - 1));

                int temp = imagePlace[firstRow];
                imagePlace[firstRow] = imagePlace[secondRow];
                imagePlace[secondRow] = temp;

                firstImage = null;
                finishControl();
            }
        }
    };


    public void finishControl(){
        String s = "";
        for(int i = 0; i < imagePlace.length; i++)
            s += String.valueOf(imagePlace[i]);

        if("012345678".equals(s))
            Toast.makeText(this, "Oyun Bitti", Toast.LENGTH_LONG).show();
    }

}
