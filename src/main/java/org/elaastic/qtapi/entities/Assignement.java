package org.elaastic.qtapi.entities;

import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import java.util.Date;

public class Assignement {

        @NotBlank
        private String title;
        private User owner;

        private Date dateCreated;
        private Date lastUpdated;
        @Nullable
        private String globalId;

        /**
         * Get the sequences
         * @return the sequences
         */
//        public List<Sequence> getSequences() {
//            return Sequence.findAllByAssignment(this,[sort:'rank', order:'asc'])
//        }

        public Integer countSequences() {
            return Sequence.countByAssignment(this);
        }

        /**
         * Get the last sequence of the current assignment
         * @return
         */
        public Sequence getLastSequence() {
            Sequence res = null;
            if (sequences) {
                res = sequences.last();
            }
            return res;
        }

        /**
         * Count the number of registered users
         * @return the count
         */
        public Integer registeredUserCount() {
            return LearnerAssignment.countByAssignment(this);
        }
    }

}
