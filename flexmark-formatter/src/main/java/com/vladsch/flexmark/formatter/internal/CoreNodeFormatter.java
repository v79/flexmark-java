package com.vladsch.flexmark.formatter.internal;

import com.vladsch.flexmark.ast.*;
import com.vladsch.flexmark.ast.util.ReferenceRepository;
import com.vladsch.flexmark.formatter.CustomNodeFormatter;
import com.vladsch.flexmark.formatter.options.ElementPlacement;
import com.vladsch.flexmark.formatter.options.ElementPlacementSort;
import com.vladsch.flexmark.formatter.options.KeepAtStartOfLine;
import com.vladsch.flexmark.formatter.options.ListSpacing;
import com.vladsch.flexmark.parser.ListOptions;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.Ref;
import com.vladsch.flexmark.util.Utils;
import com.vladsch.flexmark.util.html.FormattingAppendable;
import com.vladsch.flexmark.util.options.DataHolder;
import com.vladsch.flexmark.util.options.DataKey;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import com.vladsch.flexmark.util.sequence.RepeatedCharSequence;

import java.util.*;

import static com.vladsch.flexmark.formatter.options.DiscretionaryText.ADD;
import static com.vladsch.flexmark.formatter.options.DiscretionaryText.AS_IS;
import static com.vladsch.flexmark.util.sequence.BasedSequence.NULL;

@SuppressWarnings("WeakerAccess")
public class CoreNodeFormatter extends NodeRepositoryFormatter<ReferenceRepository, Reference, RefNode> {
    public static final DataKey<Integer> LIST_ITEM_NUMBER = new DataKey<>("LIST_ITEM_NUMBER", 0);
    public static final DataKey<ListSpacing> LIST_ITEM_SPACING = new DataKey<>("LIST_ITEM_SPACING", (ListSpacing) null);

    private final FormatterOptions options;
    private final ListOptions listOptions;
    private int blankLines;

    public CoreNodeFormatter(DataHolder options) {
        super(options);
        this.options = new FormatterOptions(options);
        this.listOptions = ListOptions.getFrom(options);
        blankLines = 0;
    }

    @Override
    public Set<Class<?>> getNodeClasses() {
        if (options.referencePlacement != ElementPlacement.AS_IS && options.referenceSort != ElementPlacementSort.SORT_UNUSED_LAST) return null;
        //noinspection unchecked,ArraysAsListWithZeroOrOneArgument
        return new HashSet<Class<?>>(Arrays.asList(
                RefNode.class
        ));
    }

    @Override
    public ReferenceRepository getRepository(final DataHolder options) {
        return options.get(Parser.REFERENCES);
    }

    @Override
    public ElementPlacement getReferencePlacement() {
        return options.referencePlacement;
    }

    @Override
    public ElementPlacementSort getReferenceSort() {
        return options.referenceSort;
    }

    @Override
    public void renderReferenceBlock(final Reference node, final NodeFormatterContext context, final MarkdownWriter markdown) {
        markdown.append(node.getChars()).line();
    }

    @Override
    public Set<NodeFormattingHandler<?>> getNodeFormattingHandlers() {
        return new HashSet<>(Arrays.asList(
                // Generic unknown node formatter
                new NodeFormattingHandler<>(Node.class, new CustomNodeFormatter<Node>() {
                    @Override
                    public void render(Node node, NodeFormatterContext context, MarkdownWriter markdown) {
                        CoreNodeFormatter.this.render(node, context, markdown);
                    }
                }),

                new NodeFormattingHandler<>(AutoLink.class, new CustomNodeFormatter<AutoLink>() {
                    @Override
                    public void render(AutoLink node, NodeFormatterContext context, MarkdownWriter markdown) {
                        CoreNodeFormatter.this.render(node, context, markdown);
                    }
                }),
                new NodeFormattingHandler<>(BlankLine.class, new CustomNodeFormatter<BlankLine>() {
                    @Override
                    public void render(BlankLine node, NodeFormatterContext context, MarkdownWriter markdown) {
                        CoreNodeFormatter.this.render(node, context, markdown);
                    }
                }),
                new NodeFormattingHandler<>(BlockQuote.class, new CustomNodeFormatter<BlockQuote>() {
                    @Override
                    public void render(BlockQuote node, NodeFormatterContext context, MarkdownWriter markdown) {
                        CoreNodeFormatter.this.render(node, context, markdown);
                    }
                }),
                new NodeFormattingHandler<>(Code.class, new CustomNodeFormatter<Code>() {
                    @Override
                    public void render(Code node, NodeFormatterContext context, MarkdownWriter markdown) {
                        CoreNodeFormatter.this.render(node, context, markdown);
                    }
                }),
                new NodeFormattingHandler<>(Document.class, new CustomNodeFormatter<Document>() {
                    @Override
                    public void render(Document node, NodeFormatterContext context, MarkdownWriter markdown) {
                        CoreNodeFormatter.this.render(node, context, markdown);
                    }
                }),
                new NodeFormattingHandler<>(Emphasis.class, new CustomNodeFormatter<Emphasis>() {
                    @Override
                    public void render(Emphasis node, NodeFormatterContext context, MarkdownWriter markdown) {
                        CoreNodeFormatter.this.render(node, context, markdown);
                    }
                }),
                new NodeFormattingHandler<>(FencedCodeBlock.class, new CustomNodeFormatter<FencedCodeBlock>() {
                    @Override
                    public void render(FencedCodeBlock node, NodeFormatterContext context, MarkdownWriter markdown) {
                        CoreNodeFormatter.this.render(node, context, markdown);
                    }
                }),
                new NodeFormattingHandler<>(HardLineBreak.class, new CustomNodeFormatter<HardLineBreak>() {
                    @Override
                    public void render(HardLineBreak node, NodeFormatterContext context, MarkdownWriter markdown) {
                        CoreNodeFormatter.this.render(node, context, markdown);
                    }
                }),
                new NodeFormattingHandler<>(Heading.class, new CustomNodeFormatter<Heading>() {
                    @Override
                    public void render(Heading node, NodeFormatterContext context, MarkdownWriter markdown) {
                        CoreNodeFormatter.this.render(node, context, markdown);
                    }
                }),
                new NodeFormattingHandler<>(HtmlBlock.class, new CustomNodeFormatter<HtmlBlock>() {
                    @Override
                    public void render(HtmlBlock node, NodeFormatterContext context, MarkdownWriter markdown) {
                        CoreNodeFormatter.this.render(node, context, markdown);
                    }
                }),
                new NodeFormattingHandler<>(HtmlCommentBlock.class, new CustomNodeFormatter<HtmlCommentBlock>() {
                    @Override
                    public void render(HtmlCommentBlock node, NodeFormatterContext context, MarkdownWriter markdown) {
                        CoreNodeFormatter.this.render(node, context, markdown);
                    }
                }),
                new NodeFormattingHandler<>(HtmlInnerBlock.class, new CustomNodeFormatter<HtmlInnerBlock>() {
                    @Override
                    public void render(HtmlInnerBlock node, NodeFormatterContext context, MarkdownWriter markdown) {
                        CoreNodeFormatter.this.render(node, context, markdown);
                    }
                }),
                new NodeFormattingHandler<>(HtmlInnerBlockComment.class, new CustomNodeFormatter<HtmlInnerBlockComment>() {
                    @Override
                    public void render(HtmlInnerBlockComment node, NodeFormatterContext context, MarkdownWriter markdown) {
                        CoreNodeFormatter.this.render(node, context, markdown);
                    }
                }),
                new NodeFormattingHandler<>(HtmlEntity.class, new CustomNodeFormatter<HtmlEntity>() {
                    @Override
                    public void render(HtmlEntity node, NodeFormatterContext context, MarkdownWriter markdown) {
                        CoreNodeFormatter.this.render(node, context, markdown);
                    }
                }),
                new NodeFormattingHandler<>(HtmlInline.class, new CustomNodeFormatter<HtmlInline>() {
                    @Override
                    public void render(HtmlInline node, NodeFormatterContext context, MarkdownWriter markdown) {
                        CoreNodeFormatter.this.render(node, context, markdown);
                    }
                }),
                new NodeFormattingHandler<>(HtmlInlineComment.class, new CustomNodeFormatter<HtmlInlineComment>() {
                    @Override
                    public void render(HtmlInlineComment node, NodeFormatterContext context, MarkdownWriter markdown) {
                        CoreNodeFormatter.this.render(node, context, markdown);
                    }
                }),
                new NodeFormattingHandler<>(Image.class, new CustomNodeFormatter<Image>() {
                    @Override
                    public void render(Image node, NodeFormatterContext context, MarkdownWriter markdown) {
                        CoreNodeFormatter.this.render(node, context, markdown);
                    }
                }),
                new NodeFormattingHandler<>(ImageRef.class, new CustomNodeFormatter<ImageRef>() {
                    @Override
                    public void render(ImageRef node, NodeFormatterContext context, MarkdownWriter markdown) {
                        CoreNodeFormatter.this.render(node, context, markdown);
                    }
                }),
                new NodeFormattingHandler<>(IndentedCodeBlock.class, new CustomNodeFormatter<IndentedCodeBlock>() {
                    @Override
                    public void render(IndentedCodeBlock node, NodeFormatterContext context, MarkdownWriter markdown) {
                        CoreNodeFormatter.this.render(node, context, markdown);
                    }
                }),
                new NodeFormattingHandler<>(Link.class, new CustomNodeFormatter<Link>() {
                    @Override
                    public void render(Link node, NodeFormatterContext context, MarkdownWriter markdown) {
                        CoreNodeFormatter.this.render(node, context, markdown);
                    }
                }),
                new NodeFormattingHandler<>(LinkRef.class, new CustomNodeFormatter<LinkRef>() {
                    @Override
                    public void render(LinkRef node, NodeFormatterContext context, MarkdownWriter markdown) {
                        CoreNodeFormatter.this.render(node, context, markdown);
                    }
                }),
                new NodeFormattingHandler<>(BulletList.class, new CustomNodeFormatter<BulletList>() {
                    @Override
                    public void render(BulletList node, NodeFormatterContext context, MarkdownWriter markdown) {
                        CoreNodeFormatter.this.render(node, context, markdown);
                    }
                }),
                new NodeFormattingHandler<>(OrderedList.class, new CustomNodeFormatter<OrderedList>() {
                    @Override
                    public void render(OrderedList node, NodeFormatterContext context, MarkdownWriter markdown) {
                        CoreNodeFormatter.this.render(node, context, markdown);
                    }
                }),
                new NodeFormattingHandler<>(BulletListItem.class, new CustomNodeFormatter<BulletListItem>() {
                    @Override
                    public void render(BulletListItem node, NodeFormatterContext context, MarkdownWriter markdown) {
                        CoreNodeFormatter.this.render(node, context, markdown);
                    }
                }),
                new NodeFormattingHandler<>(OrderedListItem.class, new CustomNodeFormatter<OrderedListItem>() {
                    @Override
                    public void render(OrderedListItem node, NodeFormatterContext context, MarkdownWriter markdown) {
                        CoreNodeFormatter.this.render(node, context, markdown);
                    }
                }),
                new NodeFormattingHandler<>(MailLink.class, new CustomNodeFormatter<MailLink>() {
                    @Override
                    public void render(MailLink node, NodeFormatterContext context, MarkdownWriter markdown) {
                        CoreNodeFormatter.this.render(node, context, markdown);
                    }
                }),
                new NodeFormattingHandler<>(Paragraph.class, new CustomNodeFormatter<Paragraph>() {
                    @Override
                    public void render(Paragraph node, NodeFormatterContext context, MarkdownWriter markdown) {
                        CoreNodeFormatter.this.render(node, context, markdown);
                    }
                }),
                new NodeFormattingHandler<>(Reference.class, new CustomNodeFormatter<Reference>() {
                    @Override
                    public void render(Reference node, NodeFormatterContext context, MarkdownWriter markdown) {
                        CoreNodeFormatter.this.render(node, context, markdown);
                    }
                }),
                new NodeFormattingHandler<>(SoftLineBreak.class, new CustomNodeFormatter<SoftLineBreak>() {
                    @Override
                    public void render(SoftLineBreak node, NodeFormatterContext context, MarkdownWriter markdown) {
                        CoreNodeFormatter.this.render(node, context, markdown);
                    }
                }),
                new NodeFormattingHandler<>(StrongEmphasis.class, new CustomNodeFormatter<StrongEmphasis>() {
                    @Override
                    public void render(StrongEmphasis node, NodeFormatterContext context, MarkdownWriter markdown) {
                        CoreNodeFormatter.this.render(node, context, markdown);
                    }
                }),
                new NodeFormattingHandler<>(Text.class, new CustomNodeFormatter<Text>() {
                    @Override
                    public void render(Text node, NodeFormatterContext context, MarkdownWriter markdown) {
                        CoreNodeFormatter.this.render(node, context, markdown);
                    }
                }),
                new NodeFormattingHandler<>(TextBase.class, new CustomNodeFormatter<TextBase>() {
                    @Override
                    public void render(TextBase node, NodeFormatterContext context, MarkdownWriter markdown) {
                        CoreNodeFormatter.this.render(node, context, markdown);
                    }
                }),
                new NodeFormattingHandler<>(ThematicBreak.class, new CustomNodeFormatter<ThematicBreak>() {
                    @Override
                    public void render(ThematicBreak node, NodeFormatterContext context, MarkdownWriter markdown) {
                        CoreNodeFormatter.this.render(node, context, markdown);
                    }
                })
        ));
    }

    private void render(Node node, NodeFormatterContext context, MarkdownWriter markdown) {
        BasedSequence chars = node.getChars();
        if (node instanceof Block) {
            BasedSequence contentChars = ((Block) node).getContentChars();
            if (chars.isNotNull()) {
                BasedSequence prefix = chars.prefixOf(contentChars);
                if (!prefix.isEmpty()) {
                    markdown.append(prefix);
                }
            }
            context.renderChildren(node);
            if (chars.isNotNull()) {
                BasedSequence suffix = chars.suffixOf(contentChars);
                if (!suffix.isEmpty()) {
                    markdown.append(suffix);
                }
            }
        } else {
            markdown.append(chars);
        }
    }

    private void render(BlankLine node, NodeFormatterContext context, MarkdownWriter markdown) {
        if (context.getDocument().get(LIST_ITEM_SPACING) == null) {
            if (!(node.getPrevious() == null || node.getPrevious() instanceof BlankLine)) {
                blankLines = 0;
            }
            blankLines++;
            if (blankLines <= options.maxBlankLines) markdown.blankLine(blankLines);
        }
    }

    private void render(Document node, NodeFormatterContext context, MarkdownWriter markdown) {
        // No rendering itself
        context.renderChildren(node);
    }

    private void render(final Heading node, final NodeFormatterContext context, final MarkdownWriter markdown) {
        markdown.blankLine();
        if (node.isAtxHeading()) {
            markdown.append(node.getOpeningMarker());
            boolean spaceAfterAtx = options.spaceAfterAtxMarker == ADD
                    || options.spaceAfterAtxMarker == AS_IS && node.getOpeningMarker().getEndOffset() < node.getText().getStartOffset();

            if (spaceAfterAtx) markdown.append(' ');
            context.renderChildren(node);
            switch (options.atxHeaderTrailingMarker) {
                case EQUALIZE:
                    if (node.getOpeningMarker().isNull()) break;
                    // fall through
                case ADD:
                    if (spaceAfterAtx) markdown.append(' ');
                    markdown.append(node.getOpeningMarker());
                    break;

                case REMOVE:
                    break;

                case AS_IS:
                default:
                    if (node.getClosingMarker().isNotNull()) {
                        if (spaceAfterAtx) markdown.append(' ');
                        markdown.append(node.getClosingMarker());
                    }
                    break;
            }
        } else {
            Ref<Integer> ref = new Ref<>(markdown.offset());
            markdown.lastOffset(ref);
            context.renderChildren(node);
            markdown.line();
            if (options.setextHeaderEqualizeMarker) {
                markdown.repeat(node.getClosingMarker().charAt(0), Utils.minLimit(markdown.offset() - ref.value, options.minSetextMarkerLength));
            } else {
                markdown.append(node.getClosingMarker());
            }
        }
        markdown.tailBlankLine();
    }

    private void render(final BlockQuote node, final NodeFormatterContext context, final MarkdownWriter markdown) {
        String prefix = node.getOpeningMarker().toString();
        boolean compactPrefix = false;

        switch (options.blockQuoteMarkers) {
            case AS_IS:
                prefix = node.getChars().baseSubSequence(node.getOpeningMarker().getStartOffset(), node.getFirstChild().getStartOffset()).toString();
                break;
            case ADD_COMPACT:
                prefix = ">";
                break;
            case ADD_COMPACT_WITH_SPACE:
                prefix = "> ";
                compactPrefix = true;
                break;
            case ADD_SPACED:
                prefix = "> ";
                break;
        }

        markdown.blankLine();
        markdown.pushPrefix();

        // create combined prefix, compact if needed
        String combinedPrefix = markdown.getPrefix().toString();
        if (compactPrefix && combinedPrefix.endsWith("> ")) {
            combinedPrefix = combinedPrefix.substring(0, combinedPrefix.length() - 1) + prefix;
        } else {
            combinedPrefix += prefix;
        }

        // delay prefix after EOL
        int options = markdown.getOptions();
        markdown.setOptions(options | FormattingAppendable.PREFIX_AFTER_PENDING_EOL);
        markdown.setPrefix(combinedPrefix);
        markdown.setOptions(options);

        context.renderChildren(node);
        markdown.popPrefix();
    }

    private void render(ThematicBreak node, NodeFormatterContext context, MarkdownWriter markdown) {
        markdown.blankLine();
        if (options.thematicBreak != null) {
            markdown.append(options.thematicBreak);
        } else {
            markdown.append(node.getChars());
        }
        markdown.tailBlankLine();
    }

    private void render(FencedCodeBlock node, NodeFormatterContext context, MarkdownWriter markdown) {
        markdown.blankLine();

        CharSequence openingMarker = node.getOpeningMarker();
        CharSequence closingMarker = node.getClosingMarker();
        char openingMarkerChar = openingMarker.charAt(0);
        char closingMarkerChar = closingMarker.length() > 0 ? closingMarker.charAt(0) : '\0';
        int openingMarkerLen = openingMarker.length();
        int closingMarkerLen = closingMarker.length();

        switch (options.fencedCodeMarkerType) {
            case ANY:
                break;
            case BACK_TICK:
                openingMarkerChar = '`';
                closingMarkerChar = openingMarkerChar;
                break;
            case TILDE:
                openingMarkerChar = '~';
                closingMarkerChar = openingMarkerChar;
                break;
        }

        if (openingMarkerLen < options.fencedCodeMarkerLength) openingMarkerLen = options.fencedCodeMarkerLength;
        if (closingMarkerLen < options.fencedCodeMarkerLength) closingMarkerLen = options.fencedCodeMarkerLength;

        openingMarker = RepeatedCharSequence.of(String.valueOf(openingMarkerChar), openingMarkerLen);
        if (options.fencedCodeMatchClosingMarker || closingMarkerChar == '\0') closingMarker = openingMarker;
        else closingMarker = RepeatedCharSequence.of(String.valueOf(closingMarkerChar), closingMarkerLen);

        markdown.append(openingMarker);
        if (options.fencedCodeSpaceBeforeInfo) markdown.append(' ');
        markdown.append(node.getInfo());
        markdown.line();

        markdown.openPreFormatted(true);
        if (options.fencedCodeMinimizeIndent) {
            List<BasedSequence> lines = node.getContentLines();
            int[] leadColumns = new int[lines.size()];
            int minSpaces = Integer.MAX_VALUE;
            int i = 0;
            for (BasedSequence line : lines) {
                leadColumns[i] = line.countLeadingColumns(0, " \t");
                minSpaces = Utils.min(minSpaces, leadColumns[i]);
                i++;
            }
            ArrayList<BasedSequence> trimmedLines = new ArrayList<>();
            if (minSpaces > 0) {
                i = 0;
                //StringBuilder out = new StringBuilder();
                //for (BasedSequence line : lines) {
                //    if (leadColumns[i] > minSpaces) out.append(RepeatedCharSequence.of(' ', leadColumns[i] - minSpaces));
                //    out.append(line.trimStart());
                //    i++;
                //}
                //markdown.append(out);
                for (BasedSequence line : lines) {
                    if (leadColumns[i] > minSpaces) markdown.repeat(' ', leadColumns[i] - minSpaces);
                    markdown.append(line.trimStart());
                    i++;
                }
            } else {
                markdown.append(node.getContentChars());
            }
        } else {
            markdown.append(node.getContentChars());
        }
        markdown.closePreFormatted();
        markdown.line().append(closingMarker).line();
        markdown.tailBlankLine();
    }

    private void render(IndentedCodeBlock node, NodeFormatterContext context, MarkdownWriter markdown) {
        markdown.blankLine();
        markdown.pushPrefix().addPrefix("    ");
        markdown.openPreFormatted(true);
        if (options.indentedCodeMinimizeIndent) {
            List<BasedSequence> lines = node.getContentLines();
            int[] leadColumns = new int[lines.size()];
            int minSpaces = Integer.MAX_VALUE;
            int i = 0;
            for (BasedSequence line : lines) {
                leadColumns[i] = line.countLeadingColumns(0, " \t");
                minSpaces = Utils.min(minSpaces, leadColumns[i]);
                i++;
            }
            if (minSpaces > 0) {
                i = 0;
                //StringBuilder out = new StringBuilder();
                //for (BasedSequence line : lines) {
                //    if (leadColumns[i] > minSpaces) out.append(RepeatedCharSequence.of(' ', leadColumns[i] - minSpaces));
                //    out.append(line.trimStart());
                //    i++;
                //}
                //markdown.append(out);
                for (BasedSequence line : lines) {
                    if (leadColumns[i] > minSpaces) markdown.repeat(' ', leadColumns[i] - minSpaces);
                    markdown.append(line.trimStart());
                    i++;
                }
            } else {
                markdown.append(node.getContentChars());
            }
        } else {
            markdown.append(node.getContentChars());
        }
        markdown.closePreFormatted();
        markdown.popPrefix();
        markdown.tailBlankLine();
    }

    private void render(final BulletList node, final NodeFormatterContext context, MarkdownWriter markdown) {
        renderList(node, context, markdown);
    }

    private void render(final OrderedList node, final NodeFormatterContext context, MarkdownWriter markdown) {
        renderList(node, context, markdown);
    }

    private void render(BulletListItem node, NodeFormatterContext context, MarkdownWriter markdown) {
        renderListItem(node, context, markdown, "");
    }

    private void render(OrderedListItem node, NodeFormatterContext context, MarkdownWriter markdown) {
        renderListItem(node, context, markdown, "");
    }

    public static void renderList(final ListBlock node, final NodeFormatterContext context, MarkdownWriter markdown) {
        ArrayList<Node> itemList = new ArrayList<>();
        Node item = node.getFirstChild();
        while (item != null) {
            itemList.add(item);
            item = item.getNext();
        }
        renderList(node, context, markdown, itemList);
    }

    public static void renderList(final ListBlock node, final NodeFormatterContext context, MarkdownWriter markdown, List<Node> itemList) {
        if (context.getFormatterOptions().listAddBlankLineBefore && !node.isOrDescendantOfType(ListItem.class)) {
            markdown.blankLine();
        }

        Document document = context.getDocument();
        ListSpacing listSpacing = document.get(LIST_ITEM_SPACING);
        int listItemNumber = document.get(LIST_ITEM_NUMBER);
        document.set(LIST_ITEM_NUMBER, node instanceof OrderedList ? ((OrderedList) node).getStartNumber() : 1);

        ListSpacing itemSpacing = null;
        switch (context.getFormatterOptions().listSpacing) {
            case AS_IS:
                break;
            case LOOSE:
                itemSpacing = ListSpacing.LOOSE;
                break;
            case TIGHT:
                itemSpacing = ListSpacing.TIGHT;
                break;
            case LOOSEN:
                itemSpacing = node.isLoose() ? ListSpacing.LOOSE : ListSpacing.TIGHT;
                break;
            case TIGHTEN: {
                itemSpacing = ListSpacing.LOOSE;
                for (Node item : itemList) {
                    if (item instanceof ListItem) {
                        if (((ListItem) item).isOwnTight() && item.getNext() != null) {
                            itemSpacing = ListSpacing.TIGHT;
                            break;
                        }
                    }
                }
                break;
            }
        }

        document.set(LIST_ITEM_SPACING, itemSpacing);
        for (Node item : itemList) {
            if (itemSpacing == ListSpacing.LOOSE && (listSpacing == null || listSpacing == ListSpacing.LOOSE)) markdown.blankLine();
            context.render(item);
        }
        document.set(LIST_ITEM_SPACING, listSpacing);
        document.set(LIST_ITEM_NUMBER, listItemNumber);
    }

    public static void renderListItem(
            final ListItem node,
            final NodeFormatterContext context,
            final MarkdownWriter markdown,
            CharSequence markerSuffix
    ) {
        final FormatterOptions options = context.getFormatterOptions();
        CharSequence openingMarker = node.getOpeningMarker();
        if (node instanceof OrderedListItem) {
            char delimiter = openingMarker.charAt(openingMarker.length() - 1);
            CharSequence number = openingMarker.subSequence(0, openingMarker.length() - 1);

            switch (options.listNumberedMarker) {
                case ANY:
                    break;
                case DOT:
                    delimiter = '.';
                    break;
                case PAREN:
                    delimiter = ')';
                    break;
                default:
                    throw new IllegalStateException("Missing case for ListNumberedMarker " + options.listNumberedMarker.name());
            }

            if (options.listRenumberItems) {
                Document document = context.getDocument();
                Integer itemNumber = document.get(LIST_ITEM_NUMBER);
                openingMarker = String.format("%d%c", itemNumber++, delimiter);
                document.set(LIST_ITEM_NUMBER, itemNumber);
            } else {
                openingMarker = String.format("%s%c", number, delimiter);
            }
        } else {
            switch (options.listBulletMarker) {
                case ANY:
                    break;
                case DASH:
                    openingMarker = "-";
                    break;
                case ASTERISK:
                    openingMarker = "*";
                    break;
                case PLUS:
                    openingMarker = "+";
                    break;
                default:
                    throw new IllegalStateException("Missing case for ListBulletMarker " + options.listBulletMarker.name());
            }
        }
        markdown.append(openingMarker).append(' ').append(markerSuffix);
        markdown.pushPrefix().addPrefix(options.itemContentIndent ? RepeatedCharSequence.of(' ', openingMarker.length() + markerSuffix.length() + 1) : "    ");
        context.renderChildren(node);
        markdown.popPrefix();
    }

    private void render(Emphasis node, NodeFormatterContext context, MarkdownWriter markdown) {
        markdown.append(node.getOpeningMarker());
        context.renderChildren(node);
        markdown.append(node.getOpeningMarker());
    }

    private void render(StrongEmphasis node, NodeFormatterContext context, MarkdownWriter markdown) {
        markdown.append(node.getOpeningMarker());
        context.renderChildren(node);
        markdown.append(node.getOpeningMarker());
    }

    @SuppressWarnings("WeakerAccess")
    public static void renderTextBlockParagraphLines(Node node, NodeFormatterContext context, MarkdownWriter markdown) {
        context.renderChildren(node);
        markdown.line();
    }

    @SuppressWarnings("WeakerAccess")
    public static void renderLooseParagraph(final Paragraph node, final NodeFormatterContext context, final MarkdownWriter markdown) {
        renderTextBlockParagraphLines(node, context, markdown);
        markdown.tailBlankLine();
    }

    private void render(final Paragraph node, final NodeFormatterContext context, final MarkdownWriter markdown) {
        if (!(node.getParent() instanceof ParagraphItemContainer)) {
            if (!node.isTrailingBlankLine() && (node.getNext() == null || node.getNext() instanceof ListBlock)) {
                renderTextBlockParagraphLines(node, context, markdown);
            } else {
                renderLooseParagraph(node, context, markdown);
            }
        } else {
            boolean isItemParagraph = ((ParagraphItemContainer) node.getParent()).isItemParagraph(node);
            if (isItemParagraph) {
                ListSpacing itemSpacing = context.getDocument().get(LIST_ITEM_SPACING);
                if (itemSpacing == ListSpacing.TIGHT) {
                    renderTextBlockParagraphLines(node, context, markdown);
                } else if (itemSpacing == ListSpacing.LOOSE) {
                    if (node.getParent().getNextAnyNot(BlankLine.class) == null) {
                        renderTextBlockParagraphLines(node, context, markdown);
                    } else {
                        renderLooseParagraph(node, context, markdown);
                    }
                } else {
                    if (!((ParagraphItemContainer) node.getParent()).isParagraphWrappingDisabled(node, listOptions, context.getOptions())) {
                        renderLooseParagraph(node, context, markdown);
                    } else {
                        renderTextBlockParagraphLines(node, context, markdown);
                    }
                }
            } else {
                renderLooseParagraph(node, context, markdown);
            }
        }
    }

    public static BasedSequence getSoftLineBreakSpan(Node node) {
        if (node == null) return NULL;

        Node lastNode = node;
        Node nextNode = node.getNext();

        while (nextNode != null && !(nextNode instanceof SoftLineBreak)) {
            lastNode = nextNode;
            nextNode = nextNode.getNext();
        }

        return Node.spanningChars(node.getChars(), lastNode.getChars());
    }

    private void render(SoftLineBreak node, NodeFormatterContext context, MarkdownWriter markdown) {
        markdown.append(node.getChars());
    }

    private void render(HardLineBreak node, NodeFormatterContext context, MarkdownWriter markdown) {
        markdown.append(node.getChars());
    }

    private void render(Text node, NodeFormatterContext context, MarkdownWriter markdown) {
        markdown.append(node.getChars());
    }

    private void render(TextBase node, NodeFormatterContext context, MarkdownWriter markdown) {
        context.renderChildren(node);
    }

    private void render(Code node, NodeFormatterContext context, MarkdownWriter markdown) {
        markdown.append(node.getOpeningMarker());
        markdown.append(node.getText());
        markdown.append(node.getOpeningMarker());
    }

    private void render(HtmlBlock node, NodeFormatterContext context, MarkdownWriter markdown) {
        if (node.hasChildren()) {
            // inner blocks handle rendering
            context.renderChildren(node);
        } else {
            markdown.append(node.getChars());
        }
    }

    private void render(HtmlCommentBlock node, NodeFormatterContext context, MarkdownWriter markdown) {
        markdown.append(node.getChars());
    }

    private void render(HtmlInnerBlock node, NodeFormatterContext context, MarkdownWriter markdown) {
        markdown.append(node.getChars());
    }

    private void render(HtmlInnerBlockComment node, NodeFormatterContext context, MarkdownWriter markdown) {
        markdown.append(node.getChars());
    }

    private void render(HtmlInline node, NodeFormatterContext context, MarkdownWriter markdown) {
        markdown.append(node.getChars());
    }

    private void render(HtmlInlineComment node, NodeFormatterContext context, MarkdownWriter markdown) {
        markdown.append(node.getChars());
    }

    private void render(Reference node, NodeFormatterContext context, MarkdownWriter markdown) {
        renderReference(node, context, markdown);
    }

    private void render(HtmlEntity node, NodeFormatterContext context, MarkdownWriter markdown) {
        markdown.append(node.getChars());
    }

    private void render(AutoLink node, NodeFormatterContext context, final MarkdownWriter markdown) {
        markdown.append(node.getChars());
    }

    private void render(MailLink node, NodeFormatterContext context, MarkdownWriter markdown) {
        markdown.append(node.getChars());
    }

    private void render(Image node, NodeFormatterContext context, MarkdownWriter markdown) {
        markdown.lineIf(options.keepImageLinksAtStart).append(node.getChars());
    }

    private void render(Link node, NodeFormatterContext context, MarkdownWriter markdown) {
        markdown.lineIf(options.keepExplicitLinksAtStart).append(node.getChars());
    }

    private void render(ImageRef node, NodeFormatterContext context, MarkdownWriter markdown) {
        markdown.append(node.getChars());
    }

    private void render(LinkRef node, NodeFormatterContext context, MarkdownWriter markdown) {
        markdown.append(node.getChars());
    }
}
