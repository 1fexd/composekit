/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fe.composekit.component.dialog

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.paneTitle
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import fe.android.compose.text.ProvideContentColorTextStyle
import fe.composekit.component.dialog.DefaultBasicAlertDialogOverride.BasicAlertDialog
import fe.material3.compat.getString
import fe.material3.compat.tokens.DialogTokens
import fe.material3.compat.value

/**
 * [Basic alert dialog dialog](https://m3.material.io/components/dialogs/overview)
 *
 * Dialogs provide important prompts in a user flow. They can require an action, communicate
 * information, or help users accomplish a task.
 *
 * ![Basic dialog
 * image](https://developer.android.com/images/reference/androidx/compose/material3/basic-dialog.png)
 *
 * This basic alert dialog expects an arbitrary content that is defined by the caller. Note that
 * your content will need to define its own styling.
 *
 * By default, the displayed dialog has the minimum height and width that the Material Design spec
 * defines. If required, these constraints can be overwritten by providing a `width` or `height`
 * [Modifier]s.
 *
 * Basic alert dialog usage with custom content:
 *
 * @sample androidx.compose.material3.samples.BasicAlertDialogSample
 * @param onDismissRequest called when the user tries to dismiss the Dialog by clicking outside or
 *   pressing the back button. This is not called when the dismiss button is clicked.
 * @param modifier the [Modifier] to be applied to this dialog's content.
 * @param properties typically platform specific properties to further configure the dialog.
 * @param content the content of the dialog
 */
@OptIn(ExperimentalMaterial3ComponentOverrideApi::class)
@ExperimentalMaterial3Api
@Composable
public fun BasicAlertDialog(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    properties: DialogProperties = DialogProperties(),
    content: @Composable () -> Unit,
) {
    with(LocalBasicAlertDialogOverride.current) {
        BasicAlertDialogOverrideScope(
            onDismissRequest = onDismissRequest,
            modifier = modifier,
            properties = properties,
            content = content
        )
            .BasicAlertDialog()
    }
}

/**
 * This override provides the default behavior of the [BasicAlertDialog] component.
 *
 * [BasicAlertDialogOverride] used when no override is specified.
 */
@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalMaterial3ComponentOverrideApi
public object DefaultBasicAlertDialogOverride : BasicAlertDialogOverride {
    @Composable
    override fun BasicAlertDialogOverrideScope.BasicAlertDialog() {
        Dialog(
            onDismissRequest = onDismissRequest,
            properties = properties,
        ) {
            val dialogPaneDescription = fe.material3.compat.getString(fe.material3.compat.Strings.Dialog)
//            val dialogPaneDescription = ""
            Box(
                modifier =
                    modifier
                        .sizeIn(minWidth = DialogDefaults.DialogMinWidth, maxWidth = DialogDefaults.DialogMaxWidth)
                        .then(Modifier.semantics { paneTitle = dialogPaneDescription }),
                propagateMinConstraints = true
            ) {
                content()
            }
        }
    }
}

@Composable
public fun AlertDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    confirmButton: @Composable () -> Unit,
    dismissButton: @Composable (() -> Unit)? = null,
    icon: @Composable (() -> Unit)? = null,
    title: @Composable (() -> Unit)? = null,
    text: @Composable (() -> Unit)? = null,
    shape: Shape = AlertDialogDefaults.shape,
    containerColor: Color = AlertDialogDefaults.containerColor,
    iconContentColor: Color = AlertDialogDefaults.iconContentColor,
    titleContentColor: Color = AlertDialogDefaults.titleContentColor,
    textContentColor: Color = AlertDialogDefaults.textContentColor,
    tonalElevation: Dp = AlertDialogDefaults.TonalElevation,
    properties: DialogProperties = DialogProperties(),
    padding: DialogPaddingOptions = DialogDefaults.DefaultDialogPadding,
    buttonSpacing: ButtonSpacingOptions = DialogDefaults.DefaultButtonsSpacing,
) {
    AlertDialogImpl(
        onDismissRequest = onDismissRequest,
        confirmButton = confirmButton,
        modifier = modifier,
        dismissButton = dismissButton,
        icon = icon,
        title = title,
        text = text,
        shape = shape,
        containerColor = containerColor,
        iconContentColor = iconContentColor,
        titleContentColor = titleContentColor,
        textContentColor = textContentColor,
        tonalElevation = tonalElevation,
        properties = properties,
        padding = padding,
        buttonSpacing = buttonSpacing,
    )
}

/** Contains default values used for [AlertDialog] and [BasicAlertDialog]. */
public object AlertDialogDefaults {
    /** The default shape for alert dialogs */
    public val shape: Shape
        @Composable get() = DialogTokens.ContainerShape.value

    /** The default container color for alert dialogs */
    public val containerColor: Color
        @Composable get() = DialogTokens.ContainerColor.value

    /** The default icon color for alert dialogs */
    public val iconContentColor: Color
        @Composable get() = DialogTokens.IconColor.value

    /** The default title color for alert dialogs */
    public val titleContentColor: Color
        @Composable get() = DialogTokens.HeadlineColor.value

    /** The default text color for alert dialogs */
    public val textContentColor: Color
        @Composable get() = DialogTokens.SupportingTextColor.value

    /** The default tonal elevation for alert dialogs */
    public val TonalElevation: Dp = 0.dp
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun AlertDialogImpl(
    onDismissRequest: () -> Unit,
    confirmButton: @Composable () -> Unit,
    modifier: Modifier,
    dismissButton: @Composable (() -> Unit)?,
    icon: @Composable (() -> Unit)?,
    title: @Composable (() -> Unit)?,
    text: @Composable (() -> Unit)?,
    shape: Shape,
    containerColor: Color,
    iconContentColor: Color,
    titleContentColor: Color,
    textContentColor: Color,
    tonalElevation: Dp,
    properties: DialogProperties,
    padding: DialogPaddingOptions,
    buttonSpacing: ButtonSpacingOptions,
) {
    BasicAlertDialog(
        onDismissRequest = onDismissRequest,
        modifier = modifier,
        properties = properties
    ) {
        AlertDialogContent(
            buttons = {
                AlertDialogFlowRow(
                    mainAxisSpacing = buttonSpacing.mainAxisSpacing,
                    crossAxisSpacing = buttonSpacing.crossAxisSpacing
                ) {
                    dismissButton?.invoke()
                    confirmButton()
                }
            },
            icon = icon,
            title = title,
            text = text,
            shape = shape,
            containerColor = containerColor,
            tonalElevation = tonalElevation,
            // Note that a button content color is provided here from the dialog's token, but in
            // most cases, TextButtons should be used for dismiss and confirm buttons. TextButtons
            // will not consume this provided content color value, and will used their own defined
            // or default colors.
            buttonContentColor = DialogTokens.ActionLabelTextColor.value,
            iconContentColor = iconContentColor,
            titleContentColor = titleContentColor,
            textContentColor = textContentColor,
            padding = padding
        )
    }
}

@Immutable
public data class DialogSizeOptions(
    public val minWidth: Dp,
    public val maxWidth: Dp,
)

@Immutable
public data class ButtonSpacingOptions(
    public val mainAxisSpacing: Dp,
    public val crossAxisSpacing: Dp,
)

@Immutable
public data class DialogPaddingOptions(
    public val box: PaddingValues,
    public val icon: PaddingValues,
    public val title: PaddingValues,
    public val text: PaddingValues,
    public val buttons: PaddingValues,
)

@Composable
internal fun AlertDialogContent(
    buttons: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    icon: (@Composable () -> Unit)?,
    title: (@Composable () -> Unit)?,
    text: @Composable (() -> Unit)?,
    shape: Shape,
    containerColor: Color,
    tonalElevation: Dp,
    buttonContentColor: Color,
    iconContentColor: Color,
    titleContentColor: Color,
    textContentColor: Color,
    padding: DialogPaddingOptions,
) {
    Surface(
        modifier = modifier,
        shape = shape,
        color = containerColor,
        tonalElevation = tonalElevation,
    ) {
        val iconContent: @Composable (ColumnScope.() -> Unit)? = icon?.let {
            {
                Box(
                    modifier = Modifier
                        .padding(padding.icon)
                        .align(Alignment.CenterHorizontally)
                ) {
                    CompositionLocalProvider(LocalContentColor provides iconContentColor) {
                        icon()
                    }
                }
            }
        }

        val titleContent: @Composable (ColumnScope.() -> Unit)? = title?.let {
            {
                Box(
                    // Align the title to the center when an icon is present.
                    modifier = Modifier
                        .padding(padding.title)
                        .align(if (icon == null) Alignment.Start else Alignment.CenterHorizontally)
                ) {
                    ProvideContentColorTextStyle(
                        contentColor = titleContentColor,
                        textStyle = DialogTokens.HeadlineFont.value
                    ) {
                        title()
                    }
                }
            }
        }

        val textContent: @Composable (ColumnScope.() -> Unit)? = text?.let {
            {
                val textStyle = DialogTokens.SupportingTextFont.value
                Box(
                    modifier = Modifier
                        .weight(weight = 1f, fill = false)
                        .padding(padding.text)
                        .align(Alignment.Start)
                ) {
                    ProvideContentColorTextStyle(
                        contentColor = textContentColor,
                        textStyle = textStyle
                    ) {
                        text()
                    }
                }
            }
        }

        Column(modifier = Modifier.padding(padding.box)) {
            iconContent?.invoke(this)
            titleContent?.invoke(this)
            textContent?.invoke(this)
            Box(modifier = Modifier
                .align(Alignment.End)
                .padding(padding.buttons)) {
                val textStyle = DialogTokens.ActionLabelTextFont.value
                ProvideContentColorTextStyle(
                    contentColor = buttonContentColor,
                    textStyle = textStyle,
                    content = buttons
                )
            }
        }
    }
}

/**
 * Interface that allows libraries to override the behavior of the [BasicAlertDialog] component.
 *
 * To override this component, implement the member function of this interface, then provide the
 * implementation to [LocalBasicAlertDialogOverride] in the Compose hierarchy.
 */
@ExperimentalMaterial3ComponentOverrideApi
public interface BasicAlertDialogOverride {
    /** Behavior function that is called by the [BasicAlertDialog] component. */
    @Composable
    public fun BasicAlertDialogOverrideScope.BasicAlertDialog()
}

/**
 * Parameters available to [BasicAlertDialog].
 *
 * @param onDismissRequest called when the user tries to dismiss the Dialog by clicking outside or
 *   pressing the back button. This is not called when the dismiss button is clicked.
 * @param modifier the [Modifier] to be applied to this dialog's content.
 * @param properties typically platform specific properties to further configure the dialog.
 * @param content the content of the dialog
 */
@ExperimentalMaterial3ComponentOverrideApi
public class BasicAlertDialogOverrideScope
internal constructor(
    public val onDismissRequest: () -> Unit,
    public val modifier: Modifier = Modifier,
    public val properties: DialogProperties = DialogProperties(),
    public val content: @Composable () -> Unit,
)

/** CompositionLocal containing the currently-selected [BasicAlertDialogOverride]. */
@ExperimentalMaterial3ComponentOverrideApi
public val LocalBasicAlertDialogOverride: ProvidableCompositionLocal<BasicAlertDialogOverride> =
    compositionLocalOf {
        DefaultBasicAlertDialogOverride
    }
