//////////////////////////////////////////////////////////////
//fonction de liaison avec le back-end
//////////////////////////////////////////////////////////////
export async function invokePost(method, data, successMsg, failureMsg) {
  const requestOptions = {
    method: "POST",
    headers: { "Content-Type": "application/json; charset=utf-8" },
    body: JSON.stringify(data)
  };
  const res = await fetch("/ProjetWeb/rest/" + method, requestOptions);
  if (res.ok) {
    console.log(successMsg);
  }
  else {
    console.log(failureMsg);
  }
}

export async function invokeGet(method, data, failureMsg) {
  
  let urlParameters = "";

  if (data != null) {
    Object.keys(data).forEach(key => {
      urlParameters += ("/" + data[key]);
    })
  }

  const res = await fetch("/ProjetWeb/rest/" + method + urlParameters);

  if (res.ok) {
    return await res.json();
  }

  //retourne null en cas d'erreur de la requette http
  console.log(failureMsg);
  return null;
}

//export async function invokeGet(method, data, successMsg, failureMsg);
//export async function invokePost(method, failureMsg);
//export { invokeGet };