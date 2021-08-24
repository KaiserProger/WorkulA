package com.swaptech.workula.presentation.uicomponents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.swaptech.workula.R

//TODO: Make it more reusable
@Composable
fun ConfirmationPopup(
    onDismissRequest: () -> Unit,
    onPositiveButtonClicked: () -> Unit,
    onNegativeButtonClicked: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismissRequest,
    ) {
        Surface(
            shape = RoundedCornerShape(16.dp)
        ) {
            Column {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Row {
                        Text(
                            modifier = Modifier.padding(
                                bottom = 8.dp
                            ),
                            text = stringResource(id = R.string.warning),
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                    }
                    Text(
                        text = stringResource(id = R.string.do_you_want_edit_this_item),
                        fontSize = 15.sp
                    )
                }
                Row {
                    OutlinedButton(
                        modifier = Modifier.weight(1f),
                        shape = RectangleShape,
                        onClick = onPositiveButtonClicked,
                        content = {
                            Text(
                                text = stringResource(id = R.string.sure),
                                color = MaterialTheme.colors.primary
                            )
                        }
                    )
                    OutlinedButton(
                        modifier = Modifier.weight(1f),
                        shape = RectangleShape,
                        onClick = onNegativeButtonClicked,
                        content = {
                            Text(
                                text = stringResource(id = R.string.cancel),
                                color = MaterialTheme.colors.primary
                            )
                        }
                    )
                }
            }
        }
    }
}