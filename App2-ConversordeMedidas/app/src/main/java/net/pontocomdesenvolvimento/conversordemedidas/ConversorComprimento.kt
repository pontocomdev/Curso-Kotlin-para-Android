package net.pontocomdesenvolvimento.conversordemedidas

import android.R.layout.simple_spinner_dropdown_item
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import java.math.BigDecimal
import java.util.*
import kotlin.Double as Double1

class ConversorComprimento : AppCompatActivity() {

    internal lateinit var mInterstitialAd: InterstitialAd
    var medidas = arrayOf("Milimetro (mm)", "Centimetro (cm)", "Metro (m)","Quilometro (km)")
    var selecao = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conversor_comprimento)

        val spinner = findViewById<Spinner>(R.id.select)
        val txtResultado = findViewById<TextView>(R.id.txtResultado)
        val txtValor = findViewById<EditText>(R.id.txtValor)
        val btnCalcular = findViewById<Button>(R.id.btnConverter)


        //Cria um ArrayAdapter
        val adapter = ArrayAdapter(this, simple_spinner_dropdown_item, medidas)
        spinner.adapter = adapter

        //Seleciona um item
        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selecao = position
            }

        }

        btnCalcular.setOnClickListener {
            val valor = txtValor.text.toString()
            if(valor != ""){
                if(selecao == 0){
                    //milimetro
                    var texto = " Centimetro = "
                    texto +=  Double.parseDouble(valor)/ 10f
                    texto += "cm \n Metro = "
                    texto += Double.parseDouble(valor) / 1000f
                    texto += "m \n Quilometro = "
                    texto += Double.parseDouble(valor) / 1000000f
                    texto += "km"

                    txtResultado.text = texto

                }else if(selecao == 1){
                    //centimetro
                    var texto = " Milimetro = "
                    texto +=  Double.parseDouble(valor)* 10f
                    texto += "mm \n Metro = "
                    texto += Double.parseDouble(valor) / 100f
                    texto += "m \n Quilometro = "
                    texto += Double.parseDouble(valor) / 100000f
                    texto += "km"

                    txtResultado.text = texto

                }else if(selecao == 2){
                    //metro
                    var texto = " Milimetro = "
                    texto +=  Double.parseDouble(valor)* 1000f
                    texto += "mm \n Centimetro = "
                    texto += Double.parseDouble(valor) * 100f
                    texto += "cm \n Quilometro = "
                    texto += Double.parseDouble(valor) / 1000f
                    texto += "km"

                    txtResultado.text = texto

                }else if(selecao == 3){
                    //Quilometro

                    var texto = " Milimetro = "
                    texto +=  Double.parseDouble(valor)* 1000000f
                    texto += "mm \n Centimetro = "
                    texto += Double.parseDouble(valor) * 100000f
                    texto += "cm \n Metro = "
                    texto += Double.parseDouble(valor) * 1000f
                    texto += "m"

                    txtResultado.text = texto
                }
            }else{
                txtResultado.text = "Adicione um valor para ser Convertido!"
            }
        }

        //Ativa o UP navigation na Action Bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mInterstitialAd = InterstitialAd(this)
        // set the ad unit ID
        mInterstitialAd.adUnitId = getString(R.string.admob_interstitial_id)
        val adRequestInterstitial = AdRequest.Builder().addTestDevice(getString(R.string.admob_test_device_id)).build()
        //Uncomment for the real devices
        //val adRequestInterstitial = AdRequest.Builder().build()
        mInterstitialAd.loadAd(adRequestInterstitial)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        //Trata o Clique no Botão Voltar
        if(item?.itemId == android.R.id.home){
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show()
                mInterstitialAd.adListener = object : AdListener() {
                    override fun onAdClosed() {
                        super.onAdClosed()
                        finish()
                    }
                }
            }else {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}

