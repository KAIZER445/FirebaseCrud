package own.project.firebasecrud

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import own.project.firebasecrud.databinding.UsersItemBinding

class UsersAdapter(
    var context: Context,
    var usersList:ArrayList<Users>) : RecyclerView.Adapter<UsersAdapter.UsersViewHolder>() {

    inner class UsersViewHolder(val adapterDataBinding: UsersItemBinding) : RecyclerView.ViewHolder(adapterDataBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val binding = UsersItemBinding.inflate(LayoutInflater.from(context),parent,false)

        return UsersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {


        holder.adapterDataBinding.textViewName.text = usersList[position].userName
        holder.adapterDataBinding.textViewAge.text = usersList[position].userAge.toString()
        holder.adapterDataBinding.textViewEmail.text = usersList[position].userEmail

        holder.adapterDataBinding.newLayout.setOnClickListener {
            val Intent = Intent(context,UpdateUserActivity::class.java)
            Intent.putExtra("id",usersList[position].userId)
            Intent.putExtra("name",usersList[position].userName)
            Intent.putExtra("email",usersList[position].userEmail)
            Intent.putExtra("age",usersList[position].userAge)
            context.startActivity(Intent)
        }
    }

    override fun getItemCount(): Int {
        return usersList.size
    }

}