package com.example.tugas_pertemuan12.ui.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tugas_pertemuan12.ui.customwidget.CostumeTopAppBar
import com.example.tugas_pertemuan12.ui.navigation.DestinasiNavigasi
import com.example.tugas_pertemuan12.ui.viewmodel.PenyediaViewModel
import com.example.tugas_pertemuan12.ui.viewmodel.UpdateViewModel
import kotlinx.coroutines.launch

object DestinasiUpdate : DestinasiNavigasi {
    override val route = "update"
    const val NIM = "nim"
    override val titleRes = "Edit Mahasiswa"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateView(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: UpdateViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val insertUiState = viewModel.updateUIState
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiUpdate.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    ) { innerPadding ->
        EntryBody(
            insertUiState = insertUiState,
            onSiswaValueChange = { viewModel.updateState(it) },
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updateMahasiswa()
                    navigateBack()
                }
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}