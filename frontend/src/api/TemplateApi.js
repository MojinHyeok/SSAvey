import { instance } from '@/api/index'

const getTemplates = (payload, callback, errorCallback) => {
  instance
    .get('/template?uid=' + payload, payload)
    .then(res => callback(res))
    .catch(err => errorCallback(err))
}
const makeTemplate = (payload, callback, errorCallback) => {
  instance
    .post(`/template`, payload)
    .then(res => callback(res))
    .catch(err => errorCallback(err))
}

const deleteTemplate = (payload, callback, errorCallback) => {
  instance
    .delete(`/template/${payload.tid}?uid=${payload.uid}`)
    .then(res => callback(res))
    .catch(err => errorCallback(err))
}
const TemplateApi = {
  getTemplates: (payload, callback, errorCallback) =>
    getTemplates(payload, callback, errorCallback),
  makeTemplate: (payload, callback, errorCallback) =>
    makeTemplate(payload, callback, errorCallback),
  deleteTemplate: (payload, callback, errorCallback) =>
    deleteTemplate(payload, callback, errorCallback),
}

export default TemplateApi
