package org.sleepy.language;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.TokenType;
import com.intellij.lexer.FlexLexer;

%%

%class SleepyLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}

FUNC=func
EXTERN_FUNC=extern_func
STRUCT=struct
IF=if
ELSE=else
RETURN=return
WHILE=while
REF=ref
MUTATES=mutates
BRACE_OPEN=\{
BRACE_CLOSE=\}
SEMICOLON=;
COMMA=,
DOT="."
PAREN_OPEN=\(
PAREN_CLOSE=\)
BAR="|"
ARROW="->"
AT=@([A-Z]|[a-z]|_)([A-Z]|[a-z]|[0-9]|_)*
CMP_OP="=="|"!="|<=?|>=?|is|in|as
SUM_OP="+"|"-"
PROD_OP="*"|"/"
EQ="="
COLON=":"
ASSIGN_OP="==="|"!=="|"<=="|">=="|"+="|"-="|"*="|"/="
BRACKET_OPEN=\[
BRACKET_CLOSE=\]
IDENTIFIER=([A-Z]|[a-z]|_)([A-Z]|[a-z]|[0-9]|_)*
INT=(0|[1-9][0-9]*)
LONG=(0|[1-9][0-9]*)l
DOUBLE=(0|[1-9][0-9]*)\.([0-9]?)+d?
FLOAT=(0|[1-9][0-9]*)((\.([0-9]?))?)+f
CHAR='([^\']|\\[0nrt'\"])'
STR=\"([^\"]|\\[0nrt'\"])*\"
HEX_INT=0x([0-9]|[A-F]|[a-f])+
COMMENT=#[^\n]*\n
WHITESPACE=[ \n\t]+

%%

<YYINITIAL> {FUNC}                           { return SleepyTokenType.KEYWORD; }
<YYINITIAL> {EXTERN_FUNC}                    { return SleepyTokenType.KEYWORD; }
<YYINITIAL> {STRUCT}                         { return SleepyTokenType.KEYWORD; }
<YYINITIAL> {IF}                             { return SleepyTokenType.KEYWORD; }
<YYINITIAL> {ELSE}                           { return SleepyTokenType.KEYWORD; }
<YYINITIAL> {RETURN}                         { return SleepyTokenType.KEYWORD; }
<YYINITIAL> {WHILE}                          { return SleepyTokenType.KEYWORD; }
<YYINITIAL> {REF}                            { return SleepyTokenType.KEYWORD; }
<YYINITIAL> {MUTATES}                        { return SleepyTokenType.KEYWORD; }

<YYINITIAL> {BRACE_OPEN}                     { return SleepyTokenType.BRACES; }
<YYINITIAL> {BRACE_CLOSE}                    { return SleepyTokenType.BRACES; }
<YYINITIAL> {SEMICOLON}                      { return SleepyTokenType.SEMICOLON; }
<YYINITIAL> {COMMA}                          { return SleepyTokenType.COMMA; }
<YYINITIAL> {DOT}                            { return SleepyTokenType.DOT; }
<YYINITIAL> {PAREN_OPEN}                     { return SleepyTokenType.PARENTHESES; }
<YYINITIAL> {PAREN_CLOSE}                    { return SleepyTokenType.PARENTHESES; }

<YYINITIAL> {BAR}                            { return SleepyTokenType.OPERATOR; }
<YYINITIAL> {ARROW}                          { return SleepyTokenType.OTHER; }
<YYINITIAL> {AT}                             { return SleepyTokenType.ANNOTATION; }
<YYINITIAL> {CMP_OP}                         { return SleepyTokenType.OPERATOR; }
<YYINITIAL> {SUM_OP}                         { return SleepyTokenType.OPERATOR; }
<YYINITIAL> {PROD_OP}                        { return SleepyTokenType.OPERATOR; }
<YYINITIAL> {EQ}                             { return SleepyTokenType.OPERATOR; }
<YYINITIAL> {COLON}                          { return SleepyTokenType.OTHER; }
<YYINITIAL> {ASSIGN_OP}                      { return SleepyTokenType.OPERATOR; }
<YYINITIAL> {BRACKET_OPEN}                   { return SleepyTokenType.BRACKETS; }
<YYINITIAL> {BRACKET_CLOSE}                  { return SleepyTokenType.BRACKETS; }
<YYINITIAL> {IDENTIFIER}                     { return SleepyTokenType.IDENTIFIER; }
<YYINITIAL> {INT}                            { return SleepyTokenType.NUMBER; }
<YYINITIAL> {LONG}                           { return SleepyTokenType.NUMBER; }
<YYINITIAL> {DOUBLE}                         { return SleepyTokenType.NUMBER; }
<YYINITIAL> {FLOAT}                          { return SleepyTokenType.NUMBER; }
<YYINITIAL> {CHAR}                           { return SleepyTokenType.STRING; }
<YYINITIAL> {STR}                            { return SleepyTokenType.STRING; }
<YYINITIAL> {HEX_INT}                        { return SleepyTokenType.NUMBER; }
<YYINITIAL> {COMMENT}                        { return SleepyTokenType.COMMENT; }
<YYINITIAL> {WHITESPACE}                     { return SleepyTokenType.OTHER; }

[^]                                          { return TokenType.BAD_CHARACTER; }