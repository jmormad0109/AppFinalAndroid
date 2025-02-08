import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.version1_1.R
import com.example.version1_1.domain.models.Partida
import com.example.version1_1.ui.adapter.ViewHolderPartida


class AdapterPartida(
    var listaPartidas: List<Partida> = emptyList(),
    private val edit: (Partida) -> Unit,
    private val delete: (Int) -> Unit
): RecyclerView.Adapter<ViewHolderPartida>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPartida {
        val layoutInflater = LayoutInflater.from(parent.context)
        val layoutItemPartida = R.layout.card_item

        return ViewHolderPartida(layoutInflater.inflate(layoutItemPartida, parent, false))
    }

    override fun getItemCount(): Int = listaPartidas.size

    override fun onBindViewHolder(holder: ViewHolderPartida, position: Int) {
        val partida = listaPartidas[position]
        holder.itemView.findViewById<ImageView>(R.id.editButton).setOnClickListener{
            edit(partida)
        }
        holder.itemView.findViewById<ImageView>(R.id.deleteButton).setOnClickListener{
            delete(position)
        }

        holder.rendereize(partida)

    }


}
