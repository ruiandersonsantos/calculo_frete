package ruianderson.com.br.calculafrete;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;


public class ResultadoActivity extends ActionBarActivity {
    //resultado--

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        TextView txtCodigo = (TextView) findViewById(R.id.txt_Codigo);
        TextView txtPrazo = (TextView) findViewById(R.id.txt_PrazoEntrega);
        TextView txtValorSemAdicionais = (TextView) findViewById(R.id.txt_ValorSemAdicionais);
        TextView txtValorMaoPropria = (TextView) findViewById(R.id.txt_ValorMaoPropria);
        TextView txtValorAvisoRecebimento = (TextView) findViewById(R.id.txt_ValorAvisoRecebimento);
        TextView txtValorDeclarado = (TextView) findViewById(R.id.txt_ValorDeclarado);
        TextView txtEntregaDomiciliar = (TextView) findViewById(R.id.txt_EntregaDomiciliar);
        TextView txtEntregaSabado = (TextView) findViewById(R.id.txt_EntregaSabado);
        TextView txtValor = (TextView) findViewById(R.id.txt_Valor);

        TextView txtMsgErro = (TextView) findViewById(R.id.txt_msg_erro);

        TableLayout tabela = (TableLayout)findViewById(R.id.tbl_resultado_ok);
        LinearLayout layoutErro = (LinearLayout)findViewById(R.id.ll_erro);

        ImageView iv_error  = (ImageView)findViewById(R.id.iv_error);
        ImageView iv_advertencia  = (ImageView)findViewById(R.id.iv_advertencia);

        Intent it = getIntent();
        Bundle params = it.getExtras();
        if (params != null) {

            if (params.getString("Erro").equals("0")){

                tabela.setVisibility(View.VISIBLE);

                txtCodigo.setText(params.getString("codigo"));
                txtPrazo.setText(params.getString("PrazoEntrega")+" Dias.");
                txtValorSemAdicionais.setText("R$ "+params.getString("ValorSemAdicionais"));

                txtValorMaoPropria.setText("R$ "+params.getString("ValorMaoPropria"));
                txtValorAvisoRecebimento.setText("R$ "+params.getString("ValorAvisoRecebimento"));
                txtValorDeclarado.setText("R$ "+params.getString("ValorDeclarado"));

                txtEntregaDomiciliar.setText((params.getString("EntregaDomiciliar").equals("S")?"SIM":"NAO"));
                txtEntregaSabado.setText((params.getString("EntregaSabado").equals("S")?"SIM":"NAO"));
                txtValor.setText("R$ "+params.getString("Valor"));


            }else if(Integer.parseInt(params.getString("Erro")) > 0){

                tabela.setVisibility(View.VISIBLE);

                txtCodigo.setText(params.getString("codigo"));
                txtPrazo.setText(params.getString("PrazoEntrega") + " Dias.");
                txtValorSemAdicionais.setText("R$ "+params.getString("ValorSemAdicionais"));

                txtValorMaoPropria.setText("R$ "+params.getString("ValorMaoPropria"));
                txtValorAvisoRecebimento.setText("R$ "+params.getString("ValorAvisoRecebimento"));
                txtValorDeclarado.setText("R$ "+params.getString("ValorDeclarado"));

                txtEntregaDomiciliar.setText((params.getString("EntregaDomiciliar").equals("S")?"SIM":"NAO"));
                txtEntregaSabado.setText((params.getString("EntregaSabado").equals("S")?"SIM":"NAO"));
                txtValor.setText("R$ "+params.getString("Valor"));

                iv_advertencia.setVisibility(View.VISIBLE);

                layoutErro.setVisibility(View.VISIBLE);
                txtMsgErro.setText(params.getString("obsFim"));

            }else{
                iv_error.setVisibility(View.VISIBLE);
                layoutErro.setVisibility(View.VISIBLE);
                txtMsgErro.setText(params.getString("MsgErro"));
            }




        }
    }


}
