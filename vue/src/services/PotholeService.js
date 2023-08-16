import http from 'axios';



export default {
  listPotholes(search) {
    return http.get('/potholes', { params: search });
  },
  getPothole(id) {
    return http.get(`/potholes/${id}`);
  },
  createPothole(pothole) {
    return http.post(`/potholes`, pothole)
  },
  updatePothole(pothole) {
    return http.put(`/potholes/${pothole.id}`, pothole)
  },
  deletePothole(id) {
    return http.delete(`/potholes/${id}`)
  },
  listPotholesOnMap() {
    return http.get('/potholes/map')
  }

}