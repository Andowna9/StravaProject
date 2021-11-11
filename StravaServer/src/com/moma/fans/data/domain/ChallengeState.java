package com.moma.fans.data.domain;

public enum ChallengeState {

        ACTIVE("Active"), FINISHED("Finished");

        private String name;
        private ChallengeState(String name) {

            this.name = name;
        }

        @Override
        public String toString() {

            return this.name;
        }
}
