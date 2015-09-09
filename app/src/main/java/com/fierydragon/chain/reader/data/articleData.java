package com.fierydragon.chain.reader.data;

import java.util.List;

/**
 * Copyright KelvinQian
 */
public class ArticleData {
    private List<ArticleEntity> article;

    public void setArticle(List<ArticleEntity> article) {
        this.article = article;
    }

    public List<ArticleEntity> getArticle() {
        return article;
    }

    public static class ArticleEntity {
        private String lesson;
        private List<WordsEntity> words;
        private String translation;
        private String title;
        private String content;

        public void setLesson(String lesson) {
            this.lesson = lesson;
        }

        public void setWords(List<WordsEntity> words) {
            this.words = words;
        }

        public void setTranslation(String translation) {
            this.translation = translation;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getLesson() {
            return lesson;
        }

        public List<WordsEntity> getWords() {
            return words;
        }

        public String getTranslation() {
            return translation;
        }

        public String getTitle() {
            return title;
        }

        public String getContent() {
            return content;
        }

        public static class WordsEntity {
            private String translation;
            private String category;
            private String word;

            public void setTranslation(String translation) {
                this.translation = translation;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public void setWord(String word) {
                this.word = word;
            }

            public String getTranslation() {
                return translation;
            }

            public String getCategory() {
                return category;
            }

            public String getWord() {
                return word;
            }
        }
    }
}
