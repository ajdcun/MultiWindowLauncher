package com.example.multiwindowlauncher;

import android.os.Build;

/**
 * Diese Klasse enthält Konstanten für die verschiedenen Windowing-Modi,
 * die in Android verwendet werden können, sowie Methoden zum Setzen dieser Modi.
 */
public class WindowingModes {

    // Methods für hidden API reflection
    /**
     * Beschreibung: Methode zum Setzen des Windowing-Modus.
     * API-Level: Ab Android 28 (API-Level 28) verfügbar.
     */
    public static final String METHOD_SET_LAUNCH_WINDOWING_MODE = "setLaunchWindowingMode";

    /**
     * Beschreibung: Methode zum Setzen der Stack-ID für das Starten.
     * API-Level: Ab Android 24 (API-Level 24) verfügbar.
     */
    public static final String METHOD_SET_LAUNCH_STACK_ID = "setLaunchStackId";

    // Stack-IDs für verschiedene Arbeitsbereiche
    /**
     * Beschreibung: Repräsentiert den Vollbild-Arbeitsbereich, in dem eine Aktivität den gesamten Bildschirm einnimmt.
     * API-Level: Ab Android 8.0 (API-Level 26) verfügbar.
     */
    public static final int WORKSPACE_STACK_FULLSCREEN = 1;

    /**
     * Beschreibung: Repräsentiert den Freeform-Arbeitsbereich, in dem Fenster frei verschoben und in der Größe angepasst werden können.
     * API-Level: Ab Android 7.0 (API-Level 24) verfügbar.
     */
    public static final int WORKSPACE_STACK_FREEFORM = 2;

    /**
     * Beschreibung: Repräsentiert den Split-Screen-Arbeitsbereich, in dem der Bildschirm in zwei Teile geteilt wird, um zwei Aktivitäten nebeneinander anzuzeigen.
     * API-Level: Ab Android 7.0 (API-Level 24) verfügbar.
     */
    public static final int WORKSPACE_STACK_SPLIT_SCREEN = 3;

    /**
     * Beschreibung: Repräsentiert den Bild-in-Bild (PiP)-Arbeitsbereich, in dem eine kleine Aktivität in einem schwebenden Fenster angezeigt wird.
     * API-Level: Ab Android 8.0 (API-Level 26) verfügbar.
     */
    public static final int WORKSPACE_STACK_PINNED = 4;

    /**
     * Beschreibung: Repräsentiert den Multi-Window-Arbeitsbereich, der für neuere Multi-Window-Features verwendet wird, um mehrere Fenster gleichzeitig darzustellen.
     * API-Level: Ab Android 9.0 (API-Level 28) verfügbar.
     */
    public static final int WORKSPACE_STACK_MULTI_WINDOW = 5;

    /**
     * Beschreibung: Repräsentiert den überlagerten Arbeitsbereich, der für schwebende Fenster oder überlagerte Aktivitäten verwendet wird.
     * API-Level: Ab Android 9.0 (API-Level 28) verfügbar.
     */
    public static final int WORKSPACE_STACK_SUPERIMPOSED = 6;

    // Mode-Ids für verschiedene Windowing modes
    /**
     * Beschreibung: Der Modus ist nicht definiert. Dies ist der Standardwert, wenn kein spezifischer Modus zugewiesen wurde.
     */
    public static final int WINDOWING_MODE_UNDEFINED = 0;

    /**
     * Beschreibung: Die Aktivität wird im Vollbildmodus ausgeführt, nimmt den gesamten Bildschirm ein.
     * API-Level: Ab Android 8.0 (API-Level 26) verfügbar.
     */
    public static final int WINDOWING_MODE_FULLSCREEN = 1;

    /**
     * Beschreibung: Die Aktivität wird im Bild-in-Bild-Modus (PiP) angezeigt. Dies ist ein spezieller Modus,
     * bei dem die Aktivität in einem kleinen, schwebenden Fenster läuft.
     * API-Level: Ab Android 8.0 (API-Level 26) verfügbar.
     */
    public static final int WINDOWING_MODE_PINNED = 2;

    /**
     * Beschreibung: Die Aktivität wird im primären Teil des Split-Screen-Modus ausgeführt.
     * API-Level: Ab Android 8.0 (API-Level 26) verfügbar.
     * Hinweis: Dies wurde in neueren Versionen von Android durch WINDOWING_MODE_MULTI_WINDOW ersetzt.
     */
    public static final int WINDOWING_MODE_SPLIT_SCREEN_PRIMARY = 3;

    /**
     * Beschreibung: Die Aktivität wird im sekundären Teil des Split-Screen-Modus ausgeführt.
     * API-Level: Ab Android 8.0 (API-Level 26) verfügbar.
     * Hinweis: Auch dies wurde durch WINDOWING_MODE_MULTI_WINDOW ersetzt.
     */
    public static final int WINDOWING_MODE_SPLIT_SCREEN_SECONDARY = 4;

    /**
     * Beschreibung: Die Aktivität wird im Freeform-Modus ausgeführt, wodurch das Fenster frei verschiebbar und in der Größe anpassbar ist.
     * API-Level: Ab Android 7.0 (API-Level 24) verfügbar. Dieser Modus ist allerdings standardmäßig auf den meisten Geräten deaktiviert
     * und muss explizit aktiviert werden.
     */
    public static final int WINDOWING_MODE_FREEFORM = 5;

    /**
     * Beschreibung: Die Aktivität läuft in einem Multi-Window-Modus, welcher den neuen Split-Screen-Modus für neuere Android-Versionen ersetzt.
     * API-Level: Ab Android 9.0 (API-Level 28) verfügbar.
     */
    public static final int WINDOWING_MODE_MULTI_WINDOW = 6;

    /**
     * Beschreibung: Die Aktivität läuft in einem überlagerten Multi-Window-Modus, meist verwendet für Floating Windows
     * (wie Chat Heads oder überlagerte Aktivitäten).
     * API-Level: Ab Android 9.0 (API-Level 28) verfügbar.
     */
    public static final int WINDOWING_MODE_MULTI_WINDOW_SUPERIMPOSED = 7;

    /**
     * Beschreibung: Die Aktivität wird entweder im Vollbild- oder im sekundären Teil des Split-Screen-Modus ausgeführt.
     * API-Level: Ab Android 10 (API-Level 29) verfügbar.
     */
    public static final int WINDOWING_MODE_FULLSCREEN_OR_SPLIT_SCREEN_SECONDARY = 8;

    /**
     * Beschreibung: Die Aktivität wird in einem modernen Multi-Window-Modus ausgeführt, der neuere Änderungen im
     * Multi-Window-Handling unterstützt.
     * API-Level: Ab Android 11 (API-Level 30) verfügbar.
     */
    public static final int WINDOWING_MODE_MULTI_WINDOW_MODERN = 9;

    /**
     * @return Der Name der Methode zum Setzen des Windowing-Modus.
     */
    public static String getMethodName() {
        if (getCurrentApiVersion() >= 28.0f) {
            return METHOD_SET_LAUNCH_WINDOWING_MODE;
        } else {
            return METHOD_SET_LAUNCH_STACK_ID;
        }
    }

    /**
     * @return Die ID für den undefinierten Modus.
     */
    public static int getUndefinedId() {
        if (getCurrentApiVersion() >= 28.0f) {
            return WINDOWING_MODE_UNDEFINED;
        } else {
            return WORKSPACE_STACK_FULLSCREEN;
        }
    }

    /**
     * @return Die ID für den Vollbildmodus.
     */
    public static int getFullScreenId() {
        if (getCurrentApiVersion() >= 28.0f) {
            return WINDOWING_MODE_FULLSCREEN;
        } else {
            return WORKSPACE_STACK_FULLSCREEN;
        }
    }

    /**
     * @return Die ID für den PiP-Modus.
     */
    public static int getPinnedId() {
        if (getCurrentApiVersion() >= 28.0f) {
            return WINDOWING_MODE_PINNED;
        } else {
            return WORKSPACE_STACK_PINNED;
        }
    }

    /**
     * @return Die ID für den primären Split-Screen-Modus.
     */
    public static int getSplitScreenPrimaryId() {
        if (getCurrentApiVersion() >= 28.0f) {
            return WINDOWING_MODE_SPLIT_SCREEN_PRIMARY;
        } else {
            return WORKSPACE_STACK_SPLIT_SCREEN;
        }
    }

    /**
     * @return Die ID für den sekundären Split-Screen-Modus.
     */
    public static int getSplitScreenSecondaryId() {
        if (getCurrentApiVersion() >= 28.0f) {
            return WINDOWING_MODE_SPLIT_SCREEN_SECONDARY;
        } else {
            return WORKSPACE_STACK_SPLIT_SCREEN;
        }
    }

    /**
     * @return Die ID für den Freeform-Modus.
     */
    public static int getFreeFormId() {
        if (getCurrentApiVersion() >= 28.0f) {
            return WINDOWING_MODE_FREEFORM;
        } else {
            return WORKSPACE_STACK_FREEFORM;
        }
    }

    /**
     * @return Die ID für den Multi-Window-Modus.
     */
    public static int getMultiWindowId() {
        if (getCurrentApiVersion() >= 28.0f) {
            return WINDOWING_MODE_MULTI_WINDOW;
        } else {
            return WORKSPACE_STACK_MULTI_WINDOW;
        }
    }

    /**
     * @return Die ID für den überlagerten Multi-Window-Modus.
     */
    public static int getMultiWindowSuperImposedId() {
        if (getCurrentApiVersion() >= 28.0f) {
            return WINDOWING_MODE_MULTI_WINDOW_SUPERIMPOSED;
        } else {
            return WORKSPACE_STACK_SUPERIMPOSED;
        }
    }

    /**
     * @return Die ID für den Vollbildmodus oder den sekundären Split-Screen-Modus.
     */
    public static int getFullScreenOrSplitScreenSecondaryId() {
        if (getCurrentApiVersion() >= 28.0f) {
            return WINDOWING_MODE_FULLSCREEN_OR_SPLIT_SCREEN_SECONDARY;
        } else {
            return WORKSPACE_STACK_FULLSCREEN;
        }
    }

    /**
     * Gibt die aktuelle API-Version zurück.
     * @return Die aktuelle API-Version als Float-Wert.
     */
    private static float getCurrentApiVersion() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return Float.parseFloat(Build.VERSION.SDK_INT + "." + Build.VERSION.PREVIEW_SDK_INT);
        } else {
            return (float) Build.VERSION.SDK_INT;
        }
    }
}
