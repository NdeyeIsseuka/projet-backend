package uam.polytech.dstti.app_file_manager.model;

public class AgentRequest {
        private Long localisationId;
        private Long serviceId;

        public Long getLocalisationId() {
            return localisationId;
        }

        public void setLocalisationId(Long localisationId) {
            this.localisationId = localisationId;
        }

        public Long getServiceId() {
            return serviceId;
        }

        public void setServiceId(Long serviceId) {
            this.serviceId = serviceId;
        }
}
