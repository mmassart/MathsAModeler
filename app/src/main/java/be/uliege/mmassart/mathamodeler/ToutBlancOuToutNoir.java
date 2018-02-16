package be.uliege.mmassart.mathamodeler;

import android.app.Activity;
import android.app.Application;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class ToutBlancOuToutNoir extends AppCompatActivity {
    private Board adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tout_blanc_ou_tout_noir);

        final GridView gridview = (GridView) findViewById(R.id.gridview);
        int height = 3;
        int col = 3;
        gridview.setNumColumns(col);
        adapter = new Board(this,col,height);
        gridview.setAdapter(adapter);
        //GET THE DISPLAY SIZE
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int widthS = size.x;
        int heightS = size.y;
        int columnWidth = (int) ((widthS - ((col + 1) * 0.5)) /col);
        gridview.setColumnWidth(columnWidth);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(ToutBlancOuToutNoir.this, "" + position,
                        Toast.LENGTH_SHORT).show();
                adapter.selectItem(position);
                gridview.setAdapter(adapter);
                if (adapter.hasWon())
                    Toast.makeText(ToutBlancOuToutNoir.this, "CONGRATULATION YOU HAVE WON !", Toast.LENGTH_LONG).show();
            }//end OnItemClick
        });

    }
}
