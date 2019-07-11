package net.pontocomdesenvolvimento.conversordemedidas

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView


class MainActivity : AppCompatActivity() {

    private var admobAdview: AdView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Carregar Componetes
        val btnComprimento = findViewById<ImageButton>(R.id.btnComprimento)
        val btnDados = findViewById<ImageButton>(R.id.btnDados)
        val btnTemperatura = findViewById<ImageButton>(R.id.btnTemperatura)
        val btnPeso = findViewById<ImageButton>(R.id.btnPeso)


        btnComprimento.setOnClickListener {
            val intent = Intent(this, ConversorComprimento::class.java)
            startActivity(intent)
        }

        btnDados.setOnClickListener {
            val intent = Intent(this, ConversorDados::class.java)
            startActivity(intent)
        }

        btnTemperatura.setOnClickListener {
            val intent = Intent(this, ConversorTemperatura::class.java)
            startActivity(intent)
        }

        btnPeso.setOnClickListener {
            val intent = Intent(this, ConversorPeso::class.java)
            startActivity(intent)
        }

        showBannerAd()

    }

    private fun showBannerAd() {
        admobAdview = findViewById<View>(R.id.admob_adview) as AdView

        //Using XML declaration, coz getting runtime late binding issues with following code
        //admobAdview!!.adSize = AdSize.BANNER
        //admobAdview!!.adUnitId = getString(R.string.banner_home_footer)

        val adRequest = AdRequest.Builder()
            .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
            // Check the LogCat to get your test device ID
            .addTestDevice(getString(R.string.admob_test_device_id))
            .build()

        admobAdview!!.adListener = object : AdListener() {
            override fun onAdLoaded() {}

            override fun onAdClosed() {
                Toast.makeText(applicationContext, "Anuncio Fechado!", Toast.LENGTH_SHORT).show()
            }

            override fun onAdFailedToLoad(errorCode: Int) {
                Toast.makeText(applicationContext, "Erro ao Carregar anuncio! Código do Erro: $errorCode", Toast.LENGTH_SHORT).show()
            }

            override fun onAdLeftApplication() {
                Toast.makeText(applicationContext, "Anuncio Aberto!", Toast.LENGTH_SHORT).show()
            }

            override fun onAdOpened() {
                super.onAdOpened()
            }
        }

        admobAdview!!.loadAd(adRequest)
    }

}
