package com.viceriApi.todo.enums;

public enum PriorityTask {


        HIGH("HIGH"),
        MEDIUM("MEDIUM"),
        SHORT("SHORT");

        private final String Priority;

        PriorityTask(String Priority) {
            this.Priority = Priority;
        }

        public String getPriority() {
            return Priority;
        }


}

