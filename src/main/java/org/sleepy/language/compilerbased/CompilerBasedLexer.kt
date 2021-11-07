package org.sleepy.language.compilerbased

import com.intellij.lexer.Lexer
import com.intellij.lexer.LexerPosition
import com.intellij.psi.tree.IElementType
import org.sleepy.language.SleepyCompiler
import org.sleepy.language.SleepyTokens
import org.sleepy.language.Token


class CompilerBasedLexer : Lexer() {
    private var buffer: CharSequence = ""
    private var currentTokens = listOf<Token>()
    private var currentTokenIndex = 0
    private var startOffset = 0
    private var endOffset = 0

    class ConcreteLexerPosition(private val offset: Int) : LexerPosition {
        override fun getOffset(): Int = offset
        override fun getState(): Int = 0
    }

    private val currentToken get() = currentTokens[currentTokenIndex]

    override fun getState(): Int = 0

    override fun start(buffer: CharSequence, startOffset: Int, endOffset: Int, initialState: Int) {
        this.buffer = buffer
        this.currentTokenIndex = 0
        this.startOffset = startOffset
        this.endOffset = endOffset
        currentTokens = SleepyCompiler.get { tokenize(buffer.subSequence(startOffset, endOffset)) }
    }

    override fun getTokenType(): IElementType? {
        return if (currentTokenIndex == currentTokens.size) null
        else SleepyTokens.nameToType(currentToken.type)
    }

    override fun getTokenStart() = currentToken.start + startOffset
    override fun getTokenEnd() = currentToken.end + startOffset

    override fun advance() {
        currentTokenIndex += 1
    }

    override fun getCurrentPosition(): ConcreteLexerPosition {
        return ConcreteLexerPosition(currentTokens[currentTokenIndex].end + startOffset)
    }

    override fun restore(position: LexerPosition) {
        println("restore to $position.offset")
        currentTokenIndex = currentTokens.indexOfFirst { it.start + startOffset >= position.offset }
    }


    override fun getBufferSequence(): CharSequence = buffer

    override fun getBufferEnd(): Int = buffer.length
}
