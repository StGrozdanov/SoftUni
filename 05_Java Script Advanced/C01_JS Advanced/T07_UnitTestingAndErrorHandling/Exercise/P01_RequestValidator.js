function validator(obj){
    const allowedMethods = ['GET', 'POST', 'DELETE', 'CONNECT'];
    const validUri = /(?<uri>^[A-Za-z*.0-9]+[.]*$)*/g;
    const allowedVersions = ['HTTP/0.9', 'HTTP/1.0', 'HTTP/1.1', 'HTTP/2.0'];
    const validMsg = /(?<msg>^ *[^<>\\&'"]*$)/g;

    const invalidMethod = 'Invalid request header: Invalid Method';
    const invalidURI = 'Invalid request header: Invalid URI';
    const invalidVersion = 'Invalid request header: Invalid Version';
    const invalidMsg = 'Invalid request header: Invalid Message';
    
    if (!obj.hasOwnProperty('method') || !allowedMethods.includes(obj.method)){
        throw new Error(invalidMethod);
    } else if (!obj.hasOwnProperty('uri')){
        throw new Error(invalidURI);
    } else if (!obj.hasOwnProperty('version') || !allowedVersions.includes(obj.version)){
        throw new Error(invalidVersion);
    } else if (!obj.hasOwnProperty('message')){
        throw new Error(invalidMsg);
    }

    let matchUri = obj.uri.match(validUri);
    let matchMsg = obj.message.match(validMsg);

    if (!matchUri){
        throw new Error(invalidURI);
    } else if (!matchMsg){
        throw new Error(invalidMsg);
    }
    return obj;
}

let input = validator(
    {
    method: 'GET',
    uri: 'svn.public.catalog',
    version: 'HTTP/1.1',
    message: ''
  });

