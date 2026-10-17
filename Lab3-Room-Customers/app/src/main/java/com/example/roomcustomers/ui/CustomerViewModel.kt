package com.example.roomcustomers.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.roomcustomers.data.Customer
import com.example.roomcustomers.data.CustomerDao
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

class CustomerViewModel(
    private val customerDao: CustomerDao
) : ViewModel() {

    // Current search text; blank = show everyone.
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    // Switches between "all customers" and "search results" automatically.
    @OptIn(ExperimentalCoroutinesApi::class)
    val customers: Flow<List<Customer>> = _searchQuery.flatMapLatest { query ->
        if (query.isBlank()) customerDao.getAllCustomers()
        else customerDao.searchCustomers(query.trim())
    }

    fun addCustomer(name: String, age: Int, isActive: Boolean) {
        viewModelScope.launch {
            customerDao.insert(
                Customer(name = name, age = age, isActive = isActive)
            )
        }
    }

    fun deleteCustomer(customer: Customer) {
        viewModelScope.launch {
            customerDao.delete(customer)
        }
    }

    fun search(text: String) {
        _searchQuery.value = text
    }

    fun clearSearch() {
        _searchQuery.value = ""
    }
}

/** Needed because CustomerViewModel takes a CustomerDao in its constructor. */
class CustomerViewModelFactory(
    private val customerDao: CustomerDao
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CustomerViewModel::class.java)) {
            return CustomerViewModel(customerDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
