package ruianderson.com.br.calculafrete;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;


public class MainActivity extends ActionBarActivity {

    //principal--

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void abrircalculo(View v){

        Intent obj = new Intent(getBaseContext(),CalculoActivity.class);
        startActivity(obj);
    }

    public void avaliacao(View v){

        Intent obj = new Intent(getBaseContext(),AvalieActivity.class);
        startActivity(obj);

    }

}
