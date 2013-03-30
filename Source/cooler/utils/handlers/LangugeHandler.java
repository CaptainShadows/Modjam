package cooler.utils.handlers;

import cooler.utils.LanguageHelper;
import cooler.utils.Languages;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class LangugeHandler {

    public static void loadLanguages() {

        for (String localizationFile : Languages.langFiles){
            LanguageRegistry
                    .instance()
                    .loadLocalization(
                            localizationFile,
                            LanguageHelper
                                    .getLocaleFromFileName(localizationFile),
                            LanguageHelper
                                    .isXMLLanguageFile(localizationFile));
        }
    }
}