package ruianderson.com.br.calculafrete;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import javax.xml.parsers.DocumentBuilder;

import conexao.WebClient;
import model.ItemServico;
import model.Resultado;
import model.Servico;


public class CalculoActivity extends ActionBarActivity {

    private String empresa = "";
    private String senha = "";
    private String codigoServico = "";
    private String cepOrigem = "";
    private String cepDestino = "";
    private String peso = "";
    private String formato = "";
    private String comprimento = "";
    private String altura = "";
    private String largura = "";
    private String diametro = "";
    private String maoPropria = "";
    private String valorDeclarado = "";
    private String avisoRecebimento = "";
    private String urlok;

    private ProgressDialog progressBar;

    private int ll_adicionais_flag = 0;

    // variaveis para preenchimento do spinner de servicos
    Spinner sp_servico = null;
    private String[] servicos = new String[]{"Selecione o codigo do servico","SEDEX Varejo","SEDEX a Cobrar Varejo","SEDEX 10 Varejo","SEDEX Hoje Varejo","PAC Varejo"};
    private String[] codigos_servicos = new String[]{"0","40010","40045","40215","40290","41106"};

    // variaveis para preenchimento do spinner do formato da enconmenda
    Spinner sp_formato = null;
    private String[] formatos = new String[]{"Selecione o formato da encomenta","Formato caixa/pacote","Formato rolo/prisma", "Envelope"};
    private String[] codigos_formatos = new String[]{"0","1","2","3"};

    //Objetos para apresentação dos opcionais
    LinearLayout ll_add;
    Button btn_adicionais;

    //Objetos da tela em geral

    EditText edt_cepOrigem;
    EditText edt_cepDestino;
    EditText edt_peso;
    EditText edt_comprimento;
    EditText edt_altura;
    EditText edt_largura;
    EditText edt_diametro;
    EditText edt_valorDeclarado;
    CheckBox ckb_mao;
    CheckBox ckb_recebimento;

    //principal--


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculo);

        preencheSpinner();

        vinculaObejtos();


        ll_add = (LinearLayout) findViewById(R.id.ll_adicionais);
        btn_adicionais = (Button) findViewById(R.id.btn_adicionais);

    }

    private void vinculaObejtos() {

        edt_cepOrigem = (EditText) findViewById(R.id.edt_ceporigem);
        edt_cepDestino = (EditText) findViewById(R.id.edt_cepdestino);
        edt_peso = (EditText) findViewById(R.id.edt_peso);
        edt_comprimento = (EditText) findViewById(R.id.edt_comprimento);
        edt_altura = (EditText) findViewById(R.id.edt_altura);
        edt_largura = (EditText) findViewById(R.id.edt_largura);
        edt_diametro = (EditText) findViewById(R.id.edt_diametro);
        edt_valorDeclarado = (EditText) findViewById(R.id.edt_valordeclarado);
        ckb_mao = (CheckBox) findViewById(R.id.ckb_mao);
        ckb_recebimento = (CheckBox) findViewById(R.id.ckb_recebimento);

    }

    // metodo responsavel por preencher os spinner de serviço e pacote
    private void preencheSpinner() {
        // inplementação do spinner do codigo de serviço começa aqui
        ArrayAdapter<String> adapter_servico = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,servicos);
        adapter_servico.setDropDownViewResource(android.R.layout.simple_list_item_1);

        sp_servico = (Spinner) findViewById(R.id.sp_servico);
        sp_servico.setAdapter(adapter_servico);

        sp_servico.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int posicao, long id) {

                codigoServico = codigos_servicos[posicao];


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //Fim da implementação do spinner do codigo de servico
        //----------------------------------------------------------------------------------------

        // inplementação do spinner do formato da encomenda começa aqui
        ArrayAdapter<String> adapter_formato = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,formatos);
        adapter_formato.setDropDownViewResource(android.R.layout.simple_list_item_1);

        sp_servico = (Spinner) findViewById(R.id.sp_formato);
        sp_servico.setAdapter(adapter_formato);

        sp_servico.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int posicao, long id) {

                formato = codigos_formatos[posicao];

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //Fim da implementação do spinner do formato da encomenda


    }

    private String montarURL(String plc_empresa,
                             String plc_senha,
                             String plc_codigoServico,
                             String plc_cepOrigem,
                             String plc_cepDestino,
                             String plc_peso,
                             String plc_formato,
                             String plc_comprimento,
                             String plc_altura,
                             String plc_largaura,
                             String plc_diametro,
                             String plc_maoPropria,
                             String plc_valorDeclarado,
                             String plc_avisoRecebimento){
        String url = "http://ws.correios.com.br//calculador/CalcPrecoPrazo.asmx/CalcPrecoPrazo" +
                "?nCdEmpresa=".concat(plc_empresa)+"&sDsSenha=".concat(plc_senha)+"&nCdServico=".concat(plc_codigoServico)+
                "&sCepOrigem=".concat(plc_cepOrigem)+"&sCepDestino=".concat(plc_cepDestino)+"&nVlPeso=".concat(plc_peso)+
                "&nCdFormato=".concat(plc_formato)+"&nVlComprimento=".concat(plc_comprimento)+"&nVlAltura=".concat(plc_altura)+
                "&nVlLargura=".concat(plc_altura)+"&nVlDiametro=".concat(plc_diametro)+"&sCdMaoPropria=".concat(plc_maoPropria)+
                "&nVlValorDeclarado=".concat(plc_valorDeclarado)+"&sCdAvisoRecebimento=".concat(plc_avisoRecebimento);

        return url;
    }

    public void mostrarOpcionais(View v){

        if(ll_adicionais_flag ==0 ){

            ll_add.setVisibility(View.VISIBLE);
            ll_adicionais_flag = 1;
            btn_adicionais.setText("Opcionais(-)");


        }else{

            ll_add.setVisibility(View.GONE);
            ll_adicionais_flag = 0;
            btn_adicionais.setText("Opcionais(+)");


        }


    }




    public void consultar(View v){

        preencheVariaveis();

        int ok = validaCampos();

        if(ok == 0){

            preencheOpcionais();

            urlok = montarURL(
                    "%27%27","%27%27",codigoServico,edt_cepOrigem.getText().toString(),edt_cepDestino.getText().toString(),
                    edt_peso.getText().toString(),formato,edt_comprimento.getText().toString(),edt_altura.getText().toString(),
                    edt_largura.getText().toString(),edt_diametro.getText().toString(),maoPropria,valorDeclarado,
                    avisoRecebimento
            );

            if(verificaConexao()){

                  new BuscaFreteTask().execute(urlok);

            }else{

                Intent intent = new Intent(getBaseContext(),SemConexaoActivity.class);
                startActivity(intent);

            }


        }


    }

    private void preencheOpcionais() {
        if(ckb_mao.isChecked()){
            maoPropria = "S";
        }else{
            maoPropria = "N";
        }

        if(ckb_recebimento.isChecked()){
            avisoRecebimento = "S";
        }else{
            avisoRecebimento = "N";
        }

        if (!edt_valorDeclarado.getText().toString().equals("")){

            valorDeclarado = edt_valorDeclarado.getText().toString();
        }else{

            valorDeclarado = "0";
        }
    }


    private void preencheVariaveis(){
        cepOrigem = edt_cepOrigem.getText().toString();
        cepDestino = edt_cepDestino.getText().toString();
        peso = edt_peso.getText().toString();
        comprimento = edt_comprimento.getText().toString();
        altura = edt_altura.getText().toString();
        largura = edt_largura.getText().toString();
        diametro = edt_diametro.getText().toString();

    }

    private int validaCampos() {

        int retorno = 0;

        if(codigoServico.equals("0")){

            Toast.makeText(getBaseContext(), "Servico deve ser informado", Toast.LENGTH_SHORT).show();
            retorno = 1;

            return retorno;

        }

        if(cepOrigem.equals("")){

            Toast.makeText(getBaseContext(), "CEP de origem deve ser informado", Toast.LENGTH_SHORT).show();
            retorno = 1;

            return retorno;

        }

        if(cepDestino.equals("")){

            Toast.makeText(getBaseContext(), "CEP de destino deve ser informado", Toast.LENGTH_SHORT).show();
            retorno = 1;

            return retorno;

        }

        if(formato.equals("0")){

            Toast.makeText(getBaseContext(), "Formato deve ser informado", Toast.LENGTH_SHORT).show();
            retorno = 1;

            return retorno;

        }

        if(peso.equals("")){

            Toast.makeText(getBaseContext(), "Peso deve ser informado", Toast.LENGTH_SHORT).show();
            retorno = 1;

            return retorno;

        }

        if(comprimento.equals("")){

            Toast.makeText(getBaseContext(), "Comprimento deve ser informado", Toast.LENGTH_SHORT).show();
            retorno = 1;

            return retorno;

        }

        if(altura.equals("")){

            Toast.makeText(getBaseContext(), "Altura deve ser informada", Toast.LENGTH_SHORT).show();
            retorno = 1;

            return retorno;

        }



        if(largura.equals("")){

            Toast.makeText(getBaseContext(), "Largura deve ser informado", Toast.LENGTH_SHORT).show();
            retorno = 1;

            return retorno;

        }

        if(diametro.equals("")){

            Toast.makeText(getBaseContext(), "Diametro deve ser informado", Toast.LENGTH_SHORT).show();
            retorno = 1;

            return retorno;

        }

        return retorno;
    }

    private class BuscaFreteTask extends AsyncTask<String, Resultado, Resultado> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressBar = new ProgressDialog(CalculoActivity.this);
            progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressBar.setMessage("Consultando Correios....");

            progressBar.show();

        }

        @Override
        protected Resultado doInBackground(String... parametro) {

            String url = parametro[0];

            WebClient obj = new WebClient(url);

            String frete = obj.obterfrete();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Resultado objRetorno = deserializaXML(frete);

            publishProgress(objRetorno);

            return objRetorno;
        }

        @Override
        protected void onProgressUpdate(Resultado...resutado){
            super.onProgressUpdate(resutado);

            if(resutado[0].getServico().getcServico().getErro().equals("0")){
                Toast.makeText(getBaseContext(),"Consulta realizada com sucesso!",Toast.LENGTH_SHORT).show();
            }else{

                Toast.makeText(getBaseContext(),"Erro codigo "+resutado[0].getServico().getcServico().getErro(),Toast.LENGTH_SHORT).show();
            }


        }




        @Override
        protected void onPostExecute(Resultado result) {
            super.onPostExecute(result);

            progressBar.dismiss();

            //Chamar a nova tela aqui e passar o objeto preenchido

            Intent intent = new Intent(getBaseContext(),ResultadoActivity.class);

            Bundle params = new Bundle();

            params.putString("codigo",result.getServico().getcServico().getCodigo());
            params.putString("PrazoEntrega",result.getServico().getcServico().getPrazoEntrega());
            params.putString("ValorSemAdicionais",result.getServico().getcServico().getValorSemAdicionais());
            params.putString("ValorMaoPropria",result.getServico().getcServico().getValorMaoPropria());
            params.putString("ValorAvisoRecebimento",result.getServico().getcServico().getValorAvisoRecebimento());
            params.putString("ValorDeclarado",result.getServico().getcServico().getValorValorDeclarado());
            params.putString("EntregaDomiciliar",result.getServico().getcServico().getEntregaDomiciliar());
            params.putString("EntregaSabado",result.getServico().getcServico().getEntregaSabado());
            params.putString("Valor",result.getServico().getcServico().getValor());

            params.putString("Erro",result.getServico().getcServico().getErro());
            params.putString("MsgErro",result.getServico().getcServico().getMsgErro());
            params.putString("obsFim",result.getServico().getcServico().getObsFim());



            intent.putExtras(params);


            startActivity(intent);
        }
    }

    private Resultado deserializaXML(String frete) {
        DocumentBuilder db;
        XStream xStream = new XStream(new DomDriver());
        xStream.alias("cResultado", Resultado.class);
        xStream.alias("Servicos", Servico.class);
        xStream.alias("cServico", ItemServico.class);


        Resultado objRetorno = new Resultado();

        objRetorno = (Resultado)xStream.fromXML(frete);


        Log.i("teste", objRetorno.getServico().getcServico().getValor());

        return objRetorno;
    }

    private boolean verificaConexao() {
        boolean conectado;
        ConnectivityManager conectivtyManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conectivtyManager.getActiveNetworkInfo() != null
                && conectivtyManager.getActiveNetworkInfo().isAvailable()
                && conectivtyManager.getActiveNetworkInfo().isConnected()) {
            conectado = true;
        } else {
            conectado = false;
        }
        return conectado;
    }


}
