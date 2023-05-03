package de.atlas.gui.presenter;

import de.atlas.gui.Euro2DollarRechnerView;
import de.atlas.model.Euro2DollarRechner;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class Euro2DollarPresenterImplTest {

    @InjectMocks
    private Euro2DollarPresenterImpl objectUnderTest;

    @Mock
    private Euro2DollarRechnerView viewMock;

    @Mock
    private Euro2DollarRechner modelMock;

    private static final String VALID_EURO_VALUE = "10.0";


    @Test
    void onBeenden_happyDay_maskClosed() {
        objectUnderTest.onBeenden();
        verify(viewMock).close();
    }
    @Test
    void onPopulateItems_happyDay_maskInitialized() {
        objectUnderTest.onPopulateItems();
        verify(viewMock).setEuro("0");
        verify(viewMock).setDollar("0");
        verify(viewMock).setRechnenEnabled(true);
    }

    @Test
    void onRechnen_NullValueValueInEuroField_errorMessageInDollarField() {
        when(viewMock.getEuro()).thenReturn(null);
        objectUnderTest.onRechnen();
        verify(viewMock).setDollar("Wert darf nicht null sein.");
    }

    @Test
    void onRechnen_NanValueValueInEuroField_errorMessageInDollarField() {
        when(viewMock.getEuro()).thenReturn("Not a Number");
        objectUnderTest.onRechnen();
        verify(viewMock).setDollar("Keine Zahl.");
    }

    @Test
    void onRechnen_UnexpectedRuntimeExceptionInUnderlyingService_errorMessageInDollarField() {
        when(viewMock.getEuro()).thenReturn(VALID_EURO_VALUE);
        when(modelMock.calculateEuro2Dollar(anyDouble())).thenThrow(ArrayIndexOutOfBoundsException.class);
        objectUnderTest.onRechnen();
        verify(viewMock).setDollar("Ein Fehler ist aufgetreten");
    }
    @Test
    void onRechnen_ValidNumberInEuroFiled_ValuePassedToService() {
        when(viewMock.getEuro()).thenReturn(VALID_EURO_VALUE);
        when(modelMock.calculateEuro2Dollar(anyDouble())).thenReturn(0.0);
        objectUnderTest.onRechnen();
        verify(modelMock).calculateEuro2Dollar(10.0);
    }

    @Test
    void onRechnen_HappyDay_ResultInDollarField() {
        when(viewMock.getEuro()).thenReturn(VALID_EURO_VALUE);
        when(modelMock.calculateEuro2Dollar(anyDouble())).thenReturn(47.);
        objectUnderTest.onRechnen();
        verify(modelMock).calculateEuro2Dollar(10.0);
        verify(viewMock).setDollar("47,00");
    }

    @Test
    void updateRechnenActionState_NullValueValueInEuroField_rechnenDisabled() {
        when(viewMock.getEuro()).thenReturn(null);
        objectUnderTest.updateRechnenActionState();
        verify(viewMock).setRechnenEnabled(false);
    }

    @Test
    void updateRechnenActionState_NanValueValueInEuroField_rechnenDisabled() {
        when(viewMock.getEuro()).thenReturn("Not a Number");
        objectUnderTest.updateRechnenActionState();
        verify(viewMock).setRechnenEnabled(false);
    }

    void updateRechnenActionState_ValidValueValueInEuroField_rechnenDisabled() {
        when(viewMock.getEuro()).thenReturn(VALID_EURO_VALUE);
        objectUnderTest.updateRechnenActionState();
        verify(viewMock).setRechnenEnabled(true);
    }
}