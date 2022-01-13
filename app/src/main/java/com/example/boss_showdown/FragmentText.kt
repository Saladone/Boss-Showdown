package com.example.boss_showdown

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class FragmentText() : Fragment(R.layout.fragmenttext) {

    val Str0="Benvenuto su Boss Showdown.\nL'obiettivo del gioco e' quello di affrontare il BOSS per accumulare punti."
    val Str1="Si tratta di un avversario formidabile, non e' possibile batterlo.\nTuttavia, ad ogni danno che infliggi ottieni punti in modo direttamente proporzionale."
    val Str2="I punti ottenuti ti permetteranno di salire di livello.\nLivellare garantisce un potenziamento alle statistiche di attacco e di difesa, nonche' un aumento dei punti vita massimi. Piu' accumuli punti e piu' diventi forte, e piu' sara' facile ottenere ulteriori punti."
    val Str3="Nota che per usufruire della meccanica del livello e' necessario crearsi un account tramite la registrazione ed il successivo login."
    val Str4="I punti ottenuti nel corso di una partita saranno sommati al totale dei punti accumulati,\ni tuoi punti ti permetteranno di scalare la classifica dei giocatori."
    val Str5="E' possibile usufruire della possibilita' di giocare delle partite veloci in modo da giocare anche senza account,\nqui i punti non saranno accumulati e le statistiche del personaggio saranno predefinite."
    val Str6="Hai ha disposizione 4 attacchi per colpire il Boss:\nL'attacco normale infligge al Boss danni standard proporzionali al tuo attacco.\nSemplice, ma efficace."
    val Str7="L'attacco pesante infligge il 50% di danni in piu' rispetto ad un attacco standard, ma le tue difese caleranno del 25% per il prossimo attacco del Boss.\nNon usarlo se sei molto indebolito."
    val Str8="La guardia riduce i danni subiti del 50%.\nIn questo stato anche i danni non letali che subisci ti garantiranno punti."
    val Str9="La cura ti può restituire fino al 50% del PS massimi.\nUsando la cura non guadagnerai punti per quel turno, ma d'altronde piu' resisti e piu' punti potrai puntare in alto."
    val Str10="Il gioco si svolge un turno per ognuno e il Boss rispondera' adeguatamente ad ogni tua mossa.\nCiclicamente, dopo che saranno passati alcuni turni, il Boss attacchera' con un colpo devastante che ignorera' il tuo modificatore di difesa(ma non la guardia), quando questo accade il suo attacco sale e la sua difesa cala per tutta la partita.\nStai in guardia."



   override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       val view = inflater.inflate(R.layout.fragmenttext, container, false)
       val testo: TextView = view.findViewById(R.id.textView)

       val args = this.arguments

       val pos = args?.get("data")

       if(pos==0){
           testo.text=Str0
       }
       if(pos==1){
           testo.text=Str1
       }
       if(pos==2){
           testo.text=Str2
       }
       if(pos==3){
           testo.text=Str3
       }
       if(pos==4){
           testo.text=Str4
       }
       if(pos==5){
           testo.text=Str5
       }
       if(pos==6){
           testo.text=Str6
       }
       if(pos==7){
           testo.text=Str7
       }
       if(pos==8){
           testo.text=Str8
       }
       if(pos==9){
           testo.text=Str9
       }
       if(pos==10){
           testo.text=Str10
       }

       return view
   }

}