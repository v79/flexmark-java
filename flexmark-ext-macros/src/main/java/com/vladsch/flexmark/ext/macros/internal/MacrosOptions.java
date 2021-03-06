package com.vladsch.flexmark.ext.macros.internal;

import com.vladsch.flexmark.ext.macros.MacrosExtension;
import com.vladsch.flexmark.util.options.DataHolder;
import com.vladsch.flexmark.util.options.MutableDataHolder;
import com.vladsch.flexmark.util.options.MutableDataSetter;

class MacrosOptions implements MutableDataSetter {
    public final boolean sourceWrapMacroReferences;

    public MacrosOptions(DataHolder options) {
        sourceWrapMacroReferences = MacrosExtension.SOURCE_WRAP_MACRO_REFERENCES.getFrom(options);
    }

    @Override
    public MutableDataHolder setIn(final MutableDataHolder dataHolder) {
        dataHolder.set(MacrosExtension.SOURCE_WRAP_MACRO_REFERENCES, sourceWrapMacroReferences);
        return dataHolder;
    }
}
