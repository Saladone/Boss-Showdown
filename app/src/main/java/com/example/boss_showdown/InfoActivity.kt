package com.example.boss_showdown

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class InfoActivity : AppCompatActivity() {

    var pos=0
    val Str1="Benvenuto su Boss Showdown. L'obiettivo del gioco e' quello di affrontare il BOSS per accumulare punti."
    val Str2="Si tratta di un avversario formidabile, non e' possibile batterlo. Ma ad ogni danno che infliggi ottieni punti in modo direttamente proporzionale."
    val Str3="I punti ottenuti ti permetteranno di salire di livello. Livellare garantisce un potenziamento alle statistiche di attacco e di difesa, nonche' un aumento dei punti vita massimi. Piu' accumuli punti e piu' diventi forte, e piu' sara' facile ottenere ulteriori punti."
    val Str4="Nota che per usufruire della meccanica del livello e' necessario crearsi un account tramite la registrazione ed il successivo login."
    val Str5="I punti ottenuti nel corso di una partita saranno sommati al totale dei punti accumulati, i tuoi unti ti permetteranno di scalare la classifica dei giocatori."
    val Str6="E' possibile usufruire della possibilita' di giocare delle partite veloci in modo da giocare anche senza account, qui i punti non saranno accumulati e le statistiche del personaggio saranno predefinite."
    val Str7="Hai ha disposizione 4 attacchi per colpire il Boss: l'attacco normale infligge al Boss danni standard proporzionali al tuo attacco. Semplice, ma efficace."
    val Str8="L'attacco pesante infligge il 50% di danni in piu' rispetto ad un attacco standard, ma le tue difese caleranno del 25% per il prossimo attacco del Boss. Non usarlo se sei molto indebolito."
    val Str9="La guardia riduce i danni subiti del 50%. In questo stato anche i danni non letali che subisci ti garantiranno punti."
    val Str10="La cura ti può restituire fino al 50% del PS massimi. Usando la cura non guadagnerai punti per quel turno, ma d'altronde piu' resisti e piu' punti potrai puntare in alto."
    val Str11="Il gioco si svolge un turno per ognuno e il Boss rispondera' adeguatamente ad ogni tua mossa. Ciclicamente, dopo che saranno passati alcuni turni, il Boss attacchera' con un colpo devastante che ignorera' il tuo modificatore di difesa(ma non la guardia), quando questo accade i suoi parametri subiscono un aumento permanente per la partita. Stai in guardia."

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        val actionbar= supportActionBar
        actionbar!!.title= "Info sul gioco"
        actionbar.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}