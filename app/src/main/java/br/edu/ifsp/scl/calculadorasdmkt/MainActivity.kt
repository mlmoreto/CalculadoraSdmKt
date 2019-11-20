package br.edu.ifsp.scl.calculadorasdmkt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    var concatenaLcd: Boolean = true
    var ultimoNroDigitado = 0F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Primeira Linha
        seteBt.setOnClickListener(this)
        oitoBt.setOnClickListener(this)
        noveBt.setOnClickListener(this)
        adicaoBt.setOnClickListener(this)

        // quando tem somente 1 parametro o nome eh it, quando sao n tem que declar exemplo: x: Int, y: Float
        umBt.setOnClickListener {
            if (!concatenaLcd){
                lcdTv.text = ""
            }
            lcdTv.append((it as Button).text.toString())
            concatenaLcd = true
        }
        doisBt.setOnClickListener {it ->
            if (!concatenaLcd){
                lcdTv.text = ""
            }
            lcdTv.append((it as Button).text.toString())
            concatenaLcd = true
        }
        tresBt.setOnClickListener {x: View ->
            if (!concatenaLcd){
                lcdTv.text = ""
            }
            lcdTv.append((x as Button).text.toString())
            concatenaLcd = true
        }

        multiplicacaoBt.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {
                lcdTv.text = Calculadora.calcula(
                    lcdTv.text.toString().toFloat(),
                    Operador.MULTIPLICACAO
                ).toString()
                concatenaLcd = false
                ultimoNroDigitado = lcdTv.text.toString().toFloat()
            }
        })

        limpezaRecenteBt.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {
                lcdTv.text = Calculadora.limpezaRecente(lcdTv.text.toString().toFloat(), Operador.LIMPEZARECENTE).toString()
                concatenaLcd = false
            }
        })

        limpezaTotalBt.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {
                ultimoNroDigitado = 0F
                lcdTv.text = Calculadora.limpezaTotal().toString()
                concatenaLcd = false
            }
        })

        raizQuadradaBt.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {
                /*lcdTv.text = Calculadora.calculaRaizQuadrada(
                    lcdTv.text.toString().toFloat()
                ).toString()*/
                lcdTv.text = Calculadora.calcula(
                    lcdTv.text.toString().toFloat(),
                    Operador.RAIZQUADRADA
                ).toString()
                concatenaLcd = false
            }
        })

        porcentagemBt.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {
                lcdTv.text = Calculadora.calculaPorcentagem(
                    ultimoNroDigitado, lcdTv.text.toString().toFloat()
                ).toString()
                concatenaLcd = false
            }
        })

        // :: significa que nao estou chamando a funcao, mas sim passando ela como parametro
        zeroBt.setOnClickListener(::onClickZeroPontoResultadoDivisao)
        pontoBt.setOnClickListener(::onClickZeroPontoResultadoDivisao) // faz o mesmo que o metodo de cima
        resultadoBt.setOnClickListener(::onClickZeroPontoResultadoDivisao)
        divisaoBt.setOnClickListener(::onClickZeroPontoResultadoDivisao)
    }

    override fun onClick(p0: View?) {
        if (p0 == seteBt || p0 == oitoBt || p0 == noveBt){
            // Numero
            if (!concatenaLcd){
                lcdTv.text = ""
            }
            lcdTv.append((p0 as Button).text.toString())
            concatenaLcd = true
        }else{
            if (p0 == adicaoBt){
                // Adicao
                lcdTv.text = Calculadora.calcula(
                    lcdTv.text.toString().toFloat(),
                    Operador.ADICAO
                ).toString()
                concatenaLcd = false
                ultimoNroDigitado = lcdTv.text.toString().toFloat()
            }
        }
    }

    fun onClickBtNum(p0: View?) {
        if (!concatenaLcd){
            lcdTv.text = ""
        }
        lcdTv.append((p0 as Button).text.toString())
        concatenaLcd = true
    }

    fun onClickBtSub(p0: View?) {
        lcdTv.text = Calculadora.calcula(
            lcdTv.text.toString().toFloat(),
            Operador.SUBTRACAO
        ).toString()
        concatenaLcd = false
        ultimoNroDigitado = lcdTv.text.toString().toFloat()
    }

    fun onClickZeroPontoResultadoDivisao(view: View?) {
        when (view){
            zeroBt -> {
                // Limpa LCD se último clicado foi um operador
                if (!concatenaLcd) {
                    lcdTv.text = ""
                }
                lcdTv.append((view as Button).text.toString())
                concatenaLcd = true
            }
            pontoBt -> {
                if (!lcdTv.text.toString().contains(".")){
                    if (!concatenaLcd) {
                        lcdTv.text = "0"
                    }
                    lcdTv.append(".")
                    concatenaLcd = true
                }
            }
            resultadoBt -> {
                lcdTv.text = Calculadora.calcula(
                    lcdTv.text.toString().toFloat(),
                    Operador.RESULTADO
                ).toString()
                concatenaLcd = false
                ultimoNroDigitado = lcdTv.text.toString().toFloat()
            }
            divisaoBt -> {
                lcdTv.text = Calculadora.calcula(
                    lcdTv.text.toString().toFloat(),
                    Operador.DIVISAO
                ).toString()
                concatenaLcd = false
                ultimoNroDigitado = lcdTv.text.toString().toFloat()
            }
        }
    }
}
