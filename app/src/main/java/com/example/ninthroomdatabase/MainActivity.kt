package com.example.ninthroomdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ninthroomdatabase.databinding.ActivityMainBinding
import com.example.ninthroomdatabase.dependency_injection.UserAdapter
import com.example.ninthroomdatabase.local_db.entity.User
import com.example.ninthroomdatabase.view_model.MyViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), UserAdapter.OnUserClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: UserAdapter
    private val viewModel: MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            viewModel.apply {

                adapter = UserAdapter(this@MainActivity)
                rvUsers.adapter = adapter
                rvUsers.layoutManager = LinearLayoutManager(this@MainActivity)

                btnInsert.setOnClickListener {
                    insertUserToDB(
                        User(
                            name = etName.text.toString(),
                            email = etEmail.text.toString(),
                            phoneNumber = etPhone.text.toString()
                        )
                    )
                }
                users.observe(this@MainActivity, {
                    adapter.populateData(it)
                })
            }
        }
    }

    override fun onUserClicked(user: User) {
        viewModel.apply {
            val placeholder = user
            deleteUserFromDB(user)
            Snackbar.make(
                binding.root,
                "Successfully deleted user with name ${user.name}",
                Snackbar.LENGTH_LONG
            ).apply {
                setAction("Undo") {
                    insertUserToDB(placeholder)
                }
                show()
            }
        }
    }
}