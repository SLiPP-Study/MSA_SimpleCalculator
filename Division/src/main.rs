extern crate iron;
extern crate bodyparser;
extern crate rustc_serialize;

use iron::prelude::*;

#[derive(Debug, Clone, RustcDecodable)]
struct Calculation {
	operand1 : i32,
	operand2 : i32
}

fn subtraction(request: &mut Request) -> IronResult<Response> {
	
	let struct_body = request.get::<bodyparser::Struct<Calculation>>();

	let mut result : i32 = 0;
    match struct_body {
        Ok(Some(struct_body)) => {
        	println!("Parsed body:\n{:?}", struct_body);
        	result = struct_body.operand1 / struct_body.operand2;
        },
        Ok(None) => println!("No body"),
        Err(err) => println!("Error: {:?}", err)
    }

    Ok(Response::with((iron::status::Ok, format!("{:?}", result))))
}

fn main() {
    let mut chain = Chain::new(subtraction);
    Iron::new(chain).http("127.0.0.1:9004").unwrap();
}

