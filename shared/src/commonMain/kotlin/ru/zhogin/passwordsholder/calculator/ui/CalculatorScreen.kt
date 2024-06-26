package ru.zhogin.passwordsholder.calculator.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.zhogin.passwordsholder.calculator.CalculatorAction
import ru.zhogin.passwordsholder.calculator.CalculatorOperation
import ru.zhogin.passwordsholder.calculator.CalculatorState

@Composable
fun CalculatorScreen(
    calculatorState: CalculatorState,
    onAction: (CalculatorAction) -> Unit,
    onNavPassScreen: () -> Unit,
) {
    val buttonSpacing = 8.dp
    Box(
        modifier = Modifier
            .fillMaxSize()
            //.background(Color.White)
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.spacedBy(buttonSpacing),
        ) {
            Text(
                text = calculatorState.number1 + (calculatorState.operation?.symbol
                    ?: "") + calculatorState.number2,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 32.dp),
                fontWeight = FontWeight.Light,
                fontSize = 68.sp,
                color = MaterialTheme.colorScheme.onBackground,
                maxLines = 2
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculatorButton(
                    symbol = "AC",
                    modifier = Modifier
                        .aspectRatio(2f)
                        .weight(2f)
                ) {
                    if (calculatorState.number1 != calculatorState.secret) {
                        onAction(CalculatorAction.Clear)
                    } else {
                        onNavPassScreen()
                        onAction(CalculatorAction.Clear)
                    }
                }
                CalculatorButton(
                    symbol = "Del",
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                ) {
                    onAction(CalculatorAction.Delete)
                }
                CalculatorButton(
                    symbol = "/",
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                ) {
                    onAction(CalculatorAction.Operation(CalculatorOperation.Divide))
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculatorButton(
                    symbol = "7",
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                ) {
                    onAction(CalculatorAction.Number(7))
                }
                CalculatorButton(
                    symbol = "8",
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                ) {
                    onAction(CalculatorAction.Number(8))
                }
                CalculatorButton(
                    symbol = "9",
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                ) {
                    onAction(CalculatorAction.Number(9))
                }
                CalculatorButton(
                    symbol = "x",
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                ) {
                    onAction(CalculatorAction.Operation(CalculatorOperation.Multiply))
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculatorButton(
                    symbol = "4",
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                ) {
                    onAction(CalculatorAction.Number(4))
                }
                CalculatorButton(
                    symbol = "5",
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                ) {
                    onAction(CalculatorAction.Number(5))
                }
                CalculatorButton(
                    symbol = "6",
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                ) {
                    onAction(CalculatorAction.Number(6))
                }
                CalculatorButton(
                    symbol = "-",
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                ) {
                    onAction(CalculatorAction.Operation(CalculatorOperation.Subtract))
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculatorButton(
                    symbol = "1",
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                ) {
                    onAction(CalculatorAction.Number(1))
                }
                CalculatorButton(
                    symbol = "2",
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                ) {
                    onAction(CalculatorAction.Number(2))
                }
                CalculatorButton(
                    symbol = "3",
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                ) {
                    onAction(CalculatorAction.Number(3))
                }
                CalculatorButton(
                    symbol = "+",
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                ) {
                    onAction(CalculatorAction.Operation(CalculatorOperation.Add))
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculatorButton(
                    symbol = "0",
                    modifier = Modifier
                        .aspectRatio(2f)
                        .weight(2f)
                ) {
                    onAction(CalculatorAction.Number(0))
                }
                CalculatorButton(
                    symbol = ".",
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                ) {
                    onAction(CalculatorAction.Decimal)
                }
                CalculatorButton(
                    symbol = "=",
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                ) {
                    onAction(CalculatorAction.Calculate)
                }
            }
        }

    }
}

