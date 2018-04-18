import android.app.FragmentManager;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class StoreActivity extends AppCompatActivity {

    FragmentManager fm;
    DBAdapter dba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        dba = new DBAdapter(this);
        dba.open();
        fm = getFragmentManager();
        fm.beginTransaction().hide(fm.findFragmentById(R.id.update)).commit();

        Cursor data = dba.getItems();
        startManagingCursor(data);

        TextView totalAmount = (TextView) this.findViewById(R.id.total_amount_text);
        totalAmount.setText("" + (int) dba.getTotal());
    }


}
